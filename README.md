# PacMan
This is a simple PacMan game moving around a specified grid.

## Getting Started

There are 2 modes that user can choose from:
* **Gaming mode** - The game will keep listening to a valid user input until a command "EXIT" is received.
* **Normal mode** - The game will read a .txt file(default is "test3.txt" included in "test_case" folder). The program will ignore invalid  commands and print out corresponding message errors and finally print output.

## Deployment

Clone the repo and run the Main class.

## Running the tests

Run PacManTest class in "test" folder to run automated unit tests.

To test Gaming Mode, manually type each command from any test file in "test_case" folder to see the outcome.

For Normal mode, add new test case to "test_case" folder. Or you can run sample tests already provided, if you want to run different test case, just change the file name to your desired test case at line 188 in "Main" class. For example:

```
File file = new File("src/com/pacman/test/test_case/your_test.txt");
```
