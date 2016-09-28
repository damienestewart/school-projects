/*
    Damiene Stewart
    TCSS371 Section A
    Winter 2015
    
    This program enables a user to play
    a guessing game with the computer.
    The game continues until the user
    indicates they no longer wish to play.
 
    C coding style used for variable
    and function naming.
 
    Tested on MAC OSX Yosemite 10.10.2
 */

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX   100

// Function declarations

void print_instructions(int limit);
int game(int target);
void print_results(int* total_games, int* total_guesses, int* maximum_guesses);

// Main.

int main(int argc, char* argv[]) {
    int total_guesses = 0;
    int total_games = 0;
    int maximum_guesses = 0;
    
    char *response = (char*) malloc(100); // Probably too much for this program, but oh well.
    
    print_instructions(MAX);
    
    srand(time(NULL)); // Seed rand function with the current timestamp.
    int guess = 0;
    do {
        guess = game(rand() % MAX + 1);
        total_guesses += guess;
        total_games++;
        
        if (guess > maximum_guesses)
            maximum_guesses = guess;
        
        printf("\nDo you want to play again? ");
        scanf("%99s", response); // Limit input to prevent Buffer Overflow. (I think this works...)
        
    } while (response[0] != 'n' && response[0] != 'N' && (response[0] == 'y' || response[0] == 'Y'));
    
    print_results(&total_games, &total_guesses, &maximum_guesses);
    
    free(response); // Free allocated resource.
    return 0;
}

// Function definitions.

// Convey instructions to user.
void print_instructions(int limit) {
    puts("This program allows you to play a guessing game.");
    printf("I will think of a number between 1 and %d \n", limit);
    puts("and will allow you to guess until you get it.");
    puts("For each guess, I will tell you whether the");
    puts("right answer is higher or lower than your guess.");
}

// Allow user to play a game.
int game(int target) {
    int guess = 0;
    int count = 0;
    
    puts("\nI'm thinking of a number...");
    
    do {
        count++;
        printf("Your guess? ");
        scanf("%d", &guess);
        
        if (guess > target) {
            puts("lower");
        } else if (guess < target) {
            puts("higher");
        }
    } while (guess != target);
    
    printf("You got it right in %d guesses.\n", count);
    
    return count;
}

// Show user the results.
void print_results(int* total_games, int* total_guesses, int* maximum_guesses) {
    puts("\nOverall results:");
    printf("\ttotal games\t= %d\n", *total_games);
    printf("\ttotal guesses\t= %d\n", *total_guesses);
    printf("\tguesses/game\t= %f\n", *total_guesses * 1.0 / *total_games);
    printf("\tmax guesses\t= %d\n", *maximum_guesses);
}
