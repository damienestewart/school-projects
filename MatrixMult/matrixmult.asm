# Damiene Stewart
#
# This program calculates the product of
# two matrices given by infile1.txt and
# infile2.txt. The first line of these files
# should contain the matrix dimensions
# separated by space. The resulting
# output will be shown in the console.
#
# The files need to be in the same directory
# as the mars simulator.

	.globl main
	.text
main:	la $a0, ifile1
	jal fopen	# open ifile1
	move $s0, $v0	# save file descriptor for ifile1
	la $a0, ifile2
	jal fopen	# open ifile2
	move $s1, $v0	# save file descriptor for ifile2
	# parse matrix one (pmtx)
	move $a0, $s0
	la $a1, m1meta
	la $a2, mtxone
	jal pmtx
	# parse matrix two (pmtx)
	move $a0, $s1
	la $a1, m2meta
	la $a2, mtxtwo
	jal pmtx
	# get address of matrices data
	la $t0, m1meta
	la $t1, m2meta
	# set up for resultant matrix space allocation
	lh $t2, 0($t0)	# $t2 = m1row 
	lh $t3, 2($t1)	# $t3 = m2col
	# set up for ability to multiply check
	lh $t0, 2($t0)	# $t0 = m1col
	lh $t1, 0($t1)	# $t1 = m2row
	bne $t0, $t1, merror	# We can't multiply.
	# allocate space for resultant matrix.
	mul $t4, $t2, $t3	# $t4 = $t2 * $t3 = number of elements we'll have
	sll $t4, $t4, 2	# multiply elements by 4 because we need word space.
	move $a0, $t4
	li $v0, 9
	syscall	# v0 has the address of our space allocated
	move $s2, $v0
	# get transpose of second matrix
	la $a0, mtxtwo
	lw $a0, 0($a0)
	la $a1, m2meta
	jal tmtx
	move $s4, $v0
	sw $s4, mtxtwo
	# prepare for mulmtx call
	la $a0, mtxone
	lw $a0, 0($a0)
	la $a1, mtxtwo
	lw $a1, 0($a1)
	move $a2, $s2
	jal mulmtx
	# open output file
	li $v0, 13
	la $a0, ofile
	li $a1, 1
	li $a2, 0
	syscall
	move $s3, $v0
	# prepare for print call
	la $t0, m1meta
	la $t1, m2meta
	move $a0, $v0
	move $a1, $s2
	lh $a2, 0($t0)
	lh $a3, 2($t1)
	jal pres	# print result
	
cexit:	move $a0, $s0	# move file descriptor for ifile1 to $a0
	li $v0, 16	# close file syscall.
	syscall
	move $a0, $s1	# move file descriptor for ifile2 to $a0
	li $v0, 16
	syscall
	move $a0, $s3	# move file descriptor for ifile2 to $a0
	li $v0, 16
	syscall
exit:	li $v0, 10	# exit syscall.
	syscall
merror:	la $a0, multe
	li $v0, 4
	syscall
	j exit
# $a0 is address of matrix one.
# $a1 is address of matrix two.
# $a2 is address of resulting matrix buffer
mulmtx: la $t0, m1meta	# get address for matrix meta data
	la $t1, m2meta
	lh $t2, 0($t0)	# $t2 = m1rows
	lh $t3, 2($t0)	# $t3 = m1cols
	lh $t4, 0($t1)	# $t4 = m2rows
	lh $t5, 2($t1)	# $t5 = m2cols
	li $t6, 0	# $t6 is counter for looping through m1's rows
mullp:	beq $t6, $t2, mulex	# are we done iterating through m1's rows?
	li $t7, 0	# $t7 is counter for looping through m2's rows (transpose's columns)
smullp: beq $t7, $t4, mullpe	# are we done iterating through m2's rows (transpose's columns)?
	mul $t9, $t6, $t2	# $t9 = # rows in m1 * iteration
	sll $t9, $t9, 2
	add $t9, $a0, $t9	# $t9 has correct row number for m1
	
	mul $t1, $t7, $t4	# $ t1 has number of elements to skip.
	sll $t1, $t1, 2	# shift to get offset for row in matrix 2
	add $t1, $t1, $a1	# get correct row number for m2 element
	addi $t7, $t7, 1	# increment counter.
	li $t8, 0	# $t8 is counter for looping through m1's columns - to calculate shit
	li $t0, 0	# $t0 is the sum to be placed in result buffer
ssmulp: beq $t8, $t3, store	# are we done calculating stuff for this row of m1?
	# calculate position in m1 row
	sll $t5, $t8, 2
	add $s0, $t9, $t5 # $t4 points to correct value in m1 matrix
	# calculate postion in m2 row
	add $t5, $t1, $t5
	lw $s0, 0($s0)
	lw $t5, 0($t5)
	mul $s0, $t5, $s0	# calculate value.
	add $t0, $s0, $t0
	addi $t8, $t8, 1	# increment counter.
	j ssmulp
store:	sw $t0, 0($a2)	# put calculated value into appropriate place in buffer
	addi $a2, $a2, 4	# point to next location
	j smullp
mullpe: addi $t6, $t6, 1
	j mullp
mulex:	jr $ra
# $a0 specify the file to be opened.
fopen:	li $a1, 0	# specify read flag.
	li $a2, 0	# apparently ignored.
	li $v0, 13	# fopen system call.
	syscall
	blt $v0, 0, foerr	# $v0 negative on error.
	jr $ra	
foerr:	la $a0, ioerr	# print file open error
	li $v0, 4	# print string syscall
	syscall
	j exit
# $a0 should have the file descriptor for the file containing
# $a1 should have the starting address of matrix metadata (row, col)
# $a2 should have the address of pointer for heap memory
# the matrix data.
pmtx:	addi $sp, $sp, -28	# configure stack for saving $ra, $s0, and $s1
	sw $ra, 24($sp)
	sw $s0, 20($sp)
	sw $s1, 16($sp)
	sw $s2, 12($sp)
	sw $s3, 8($sp)
	sw $s4, 4($sp)
	sw $s5, 0($sp)
	move $s5, $a0	# save $a0 because getint will mess it up.
	move $s3, $a1	# save $a1 because getint will mess it up.
	move $s4, $a2	# save $a2 because getint call will mess it up.
	jal getint
	move $s0, $v0	# move retrieved row count to $s0.
	move $a1, $s3	# restore $a1.
	addi $s3, $s3, 2	# point to storage for column count.
	move $a0, $s5	# restore $a0
	sh $s0, 0($a1)	# store row count.
	jal getint
	move $s1, $v0	# move retrieved col count to $v0.
	move $a1, $s3	# restore $a1.
	sh $s1, 0($a1)	# store column count.
	mul $s2, $s0, $s1	# $s2 = $s0 * $s1 = number of bytes needed.
	sll $s2, $s2, 2	# multiply number of bytes by 4 for word storage.
	move $a0, $s2	# $a0 = amount of bytes we need.
	li $v0, 9	# prepare for sbrk call
	syscall		# $v0 has address of memory allocated.
	sw $v0, 0($s4)	# address in $a2 now points to start of mtx memory.
	move $s4, $v0	# store starting adress in $s4.
	srl $s2, $s2, 2	# return $s2 to represent number of integers.
	li $s0, 0	# start a counter.
	move $a0, $s5	# restore $a0
mtxlp:	beq $s0, $s2, epmtx	# We're done reading integers.
	jal getint
	sw $v0, 0($s4)	# store the integer in our mtx buffer.
	addi $s4, $s4, 4	# point to next integer space.
	addi $s0, $s0, 1	# increment counter.
	move $a0, $s5	# restore $a0
	j mtxlp
epmtx:  lw $s5, 0($sp)
	lw $s4, 4($sp)
	lw $s3, 8($sp)
	lw $s2, 12($sp)
	lw $s1, 16($sp)
	lw $s0, 20($sp)
	lw $ra, 24($sp)
	addi $sp, $sp, 28
	jr $ra	
# $a0 should contain file descriptor
# add error checking
# result will be in $v0
# add register saving
getint:	addi $sp, $sp, -4
	sw $ra, 0($sp)	# store $ra on stack.
	la $a1, ibuff	# load $a1 with input buffer
	li $a2, 1	# specify number of characters to read
	li $t0, 0
	li $v0, 14	# specify read file system call
gloop:	syscall		# get next character in file
	lb $t1, 0($a1)
	beq $t1, 0x20, getend	# is it a space?
	beq $t1, 0xa, getend	# is it a new line?
	beq $v0, 0x0, getend	# end of file reached? We read zero characters.
	addi, $a1, $a1, 1	# increment buffer pointer to store at next position
	addi, $t0, $t0, 1	# increment digit counter
	li $v0, 14	# restore $v0 because it now shows # of chars read.
	j gloop		# continue reading numbers
getend:	la $a0, ibuff
	move $a1, $t0
	jal atoi	# call function to convert what is in the buffer
	lw $ra, 0($sp)	# reload $ra
	addi $sp, $sp, 4	# restore stack
	jr $ra
# $a0 should contain input buffer that stores numbers as ascii
# $a1 should contain the number of digits to parse.	
atoi:	li $t0, 0	# start a counter. Function runs based on amount of digits
	la $t1, darray	# load address of darray
	add $t2, $a1, -1	# get starting index for ascii digits, ie - 2, 3, ..., n
	add $t2, $t2, $a0	# point to starting value in input buffer
	li $v0, 0		#clear result buffer
aloop:	beq $t0, $a1, aexit	# if we have read in the amount of numbers, stop.
	lb $t3, 0($t2)	# get first ascii number
	addi $t3, $t3, -0x30	# subtract hexadecimal 30 from the ascii to convert to decimal.
	lh $t4, 0($t1)	# $t4 = 1, 10, 100, 1000, or 10000
	mul $t5, $t3, $t4	# $t5 = $t3 * $t4
	add $v0, $v0, $t5	# add to sum. RESULT
	addi $t0, $t0, 1	# increment digit counter
	addi $t1, $t1, 2	# increment position in darray. 2 because it's halfword storage.
	addi $t2, $t2, -1	# decrement to point to next ascii value. 1 because it's byte storage.
	j aloop
aexit:	jr $ra
# $a1 should contain result matrix buffer
# $a2 should contain the number of rows
# $a3 should contain the number of columns
pres:	mul $t3, $a2, $a3	# $t3 contains the number of elements to print
	li $t4, 0	# $t1 is a counter for the amount of elements already written to the file
	li $t5, 0	# $t5 is a counter for position in column
	li $v0, 1	# print int call
	lw $a0, 0($a1)
ploop:	syscall
	addi $t4, $t4, 1	# increment element counter
	addi $t5, $t5, 1	# increment column counter
	bne $t5, $a3, pspc
	li $v0, 4
	la $a0, nl
	syscall
	li $t5, 0	# reset column counter
	j cont
pspc:	li $v0, 4
	la $a0, space
	syscall
cont:	addi, $a1, $a1, 4	# point to next location in result matrix buffer
	lw $a0, 0($a1)
	beq $t4, $t3, pexit	# we're done printing all the values
	li $v0, 1	# restore $v0
	j ploop
pexit: jr $ra
# $a0 should contain address of second matrix
# $a1 should contain address of second matrix dimensions
# $v0 will have transposed matrix
tmtx:	move $s1, $a0
	lh $t1, 0($a1)	# $t1 = the number of rows for the second matrix
	lh $t2, 2($a1)	# $t0 = the number of columns for the second matrix
	mul $t3, $t1, $t2	# $t3 contains the number of elements in matrix
	sll $t3, $t3, 2	# multiply elements by 4 because we need word space.
	move $a0, $t3
	li $v0, 9
	syscall	# v0 has the address of our space allocated
	move $s0, $v0
	li $t4, 0	# column counter
collp:	beq $t4, $t2, collpe	# stop if we've reached the number of columns.
	li $t6, 0	# restart row counter
	move $t5, $t4	# put counter value in $t5
	sll $t0, $t2, 2	# $t0 contains the column offset
	sll $t5, $t5, 2	# $t5 should have appropriate offset to get column.
	add $t5, $t5, $s1	# get address of element
rowlp:	beq $t6, $t1, rowlpe	# we're done with this loop.
	lw $t7, 0($t5)	# load element
	sw $t7, 0($s0)	# put element from $t5 to $s0
	addi, $s0, $s0, 4	# point to the next empty position
	addi, $t6, $t6, 1	# increment row counter
	add, $t5, $t0, $t5
	b rowlp
rowlpe:	addi $t4, $t4, 1
	b collp
collpe: jr $ra

	.data
m1meta:	.half 0, 0
m2meta:	.half 0, 0
mtxone:	.word 0
mtxtwo:	.word 0
ifile1:	.asciiz "ifile1.txt"
ifile2: .asciiz "ifile2.txt"
ofile:	.asciiz "ofile.txt"
ioerr:	.asciiz	"File could not be opened."
multe:	.asciiz	"We can't multiply these matrices."
space: .asciiz	" "
nl:	.asciiz "\n"
ibuff:	.space 5	# used as temp storage for integer
darray:	.half 1, 10, 100, 1000, 10000
