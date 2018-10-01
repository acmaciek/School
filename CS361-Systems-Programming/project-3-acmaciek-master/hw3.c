// Maciej Girek mgirek2
// HW3 Write your own shell
// CS 361 Spring 2018
// Used references rom the book "Compute Systems a Programmer's Perspective" Pages 754-756 ,763,771,773,774,777-779

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <errno.h>

#define MAX 128
#define ARGCOUNT 128

//Parse the command line and build the argv array
int parse(char *buf, char **argv)
{
    char *delim; /* Points to first space delimiter */
    int argc; /* Number of args */
    int bg; /* Background job? */
    
    buf[strlen(buf)-1] = ' '; /* Replace trailing ’\n’ with space */
    
    while (*buf && (*buf == ' ')) /* Ignore leading spaces */
        buf++;
    
    /* Build the argv list */
    argc = 0;
    while ((delim = strchr(buf, ' '))) {
        argv[argc++] = buf;
        *delim = '\0';
        buf = delim + 1;
        while (*buf && (*buf == ' ')) /* Ignore spaces */
            buf++;
    }
    argv[argc] = NULL;
    
    if (argc == 0) /* Ignore blank line */
        return 1;
    /* Should the job run in the background? */
    if ((bg = (*argv[argc-1] == '&')) != 0)
        argv[--argc] = NULL;
    
    return bg;
}

int builtin_command(char **argv)
{
    if (!strcmp(argv[0], "exit")) /* quit command */// Changed this from quit to exit
        exit(0);
    if (!strcmp(argv[0], "&")) /* Ignore singleton & */
        return 1;
    return 0; /* Not a builtin command */
}

//Evaluate a command line reference from the book pg 755
void eval(char * command_line)
{
	int file;
	char *argv[ARGCOUNT];//Argument list execve()
	char buf[MAX];//Holds modified command line
	int temp;
	int status;
	pid_t pid; //process id
    
    
	strcpy(buf, command_line);
	temp = parse(buf, argv);

	if (argv[0] == NULL)
	{
		return; //Ignore empty lines
	}

	if (!builtin_command(argv))
	{
		if ((pid = fork()) == 0)
		{
            //redirect argv
			int i;
			for (i = 0; argv[i] != NULL; i++)
			{ //snippet of code used and copied from the book
				if (strcmp(argv[i], "<") == 0) // if the command = "<"
				{
					argv[i] = '\0';
					file = open(argv[i + 1], O_RDONLY, 0);
					dup2(file, 0);
					close(file);
				}

				else if (strcmp(argv[i], ">") == 0) // if the command = ">"
				{
					argv[i] = '\0';
					file = open(argv[i + 1], O_CREAT | O_TRUNC | O_WRONLY, 0666);//This is for reading and writing
					dup2(file, 1); //if the file doesnt exist we create it and then close it
					close(file);
				}

				else if (strcmp(argv[i], ">>") == 0) // if the command = ">>"
				{
					argv[i] = '\0';
					file = open(argv[i + 1],O_RDWR|O_CREAT|O_APPEND,0644);
					dup2(file, 1);//if the file doesnt exist we create it and then close it
					close(file);
				}
			}

			if (execvp(argv[0], argv) < 0)
			{
                printf("%s: Command not found.\n", argv[0]);
                exit(0);
			}
		}

		if (wait(&status)<0)
			printf("waitfg: waitpid error");
		else if((status==0))
		{
			printf("Exit: 0\n");
		}
		printf("%i %i\n", pid, status);//print pid and status
	}
	return;
}


// SIGINT handler
void sigint_handler(int sig)
{
	write(1, "SIGINT handled\n361", 50);
}

// SIGTSTP handler
void sigtstp_handler(int sig)
{
	write(1, "SIGTSTP handled\n361> ", 50);
}

int main()
{
	char command_line[MAX];
	while (1)
	{
		printf("361> ");
		signal(SIGTSTP, sigtstp_handler);//reference from the book
		signal(SIGINT, sigint_handler);
		fgets(command_line, MAX, stdin);
		if (feof(stdin)) 
			exit(0);
		eval(command_line);//evaluate
	}
}



