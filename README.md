# DirectoryTreeGenerator
This is a kotlin script that generates the directory tree of a given path showcasing the directory structure; similar to window's Tree command.

## Purpose
This is my attempt to show how to harness the power of kotlin-scripting, regardless of the operating system in use.

## Usage
Download/Clone the project on your machine, in your terminal (Powershell/CMD) navigate to the diretory containing the scripts.
In your powershell (Windows) run ```kotlin script.main.kts  "Path of your dir" 2```. The arguments essentially represent the path of the directory to be represented and the maximum depth to be used by the program.
Both arguments are optional and if you don't provide them, the script will use the default ones. For the path of your dir, the current directory
is set as the default whereas for the maximum depth is by default set to 4.

## Requirements
Kotlin compiler installed on your machine [preferable 1.4]

Java sdk [preferably 8+]

Powershell/Commdand line


## Additional Notes
Currently the script is tested on Windows 10, so it may or may not on Unix System.
With that said, given that the default windows CMD does not support unicode characters, I have not included emojis.

