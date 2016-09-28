# Damiene Stewart
# This program calculates the factorial of a number, n, recursively.

	.data	
equal: .asciiz " = "
sign: .asciiz "!"

	.globl main

	.text
main:		li $a0, 8
		move $s0, $a0
		jal factorial
		move $a0, $s0
		move $a1, $v0
		jal print
		li $v0, 10
		syscall

factorial:	addi $sp, $sp, -8	# stack setup
		sw $ra, 4($sp)
		sw $s0, 0($sp)		
		move $s0, $a0		# $s0 = n
		addi $a0, $a0, -1 	# n - 1
		blez $a0, base				
		jal factorial		# factorial(n-1)	
		mul $v0, $s0, $v0	# v0 = n * factorial(n-1)		
exit:		lw $s0, 0($sp)		# dismantle stack
		lw $ra, 4($sp)
		addi $sp, $sp, 8
		jr $ra		
base:		li $v0, 1		# we hit base case, return 1
		j exit

#################
# Print function
#################
print:		li $v0, 1
		syscall
		la $a0, sign
		li $v0, 4
		syscall
		la $a0, equal
		syscall
		move $a0, $a1
		li $v0, 1
		syscall	
		jr $ra
