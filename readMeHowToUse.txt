This program works by either import the whole project into Eclipse or by running the Tracker.jar provided.

The Tracker.jar has to be provided only one of the three report types 1. -pl  2. -pf   3. -po
and it can also be provided the flag '-f <<filepath here>>' (OPTIONAL) to redirect the report output
to an existing/non-existing file. If the filein -f does not exist, program will create any missing parent directories and the file itself.

The Tracker.jar also checks the location res/repository.jar relative to the Tracker.jar directory to see if it exists.
If it doesn't exist, Tracker.jar creates the missing directories and the file itself.

You can run the jar file via powershell with the command: java -jar <<filename>> <<args>>

eg. java -jar Tracker.jar -f reportDir/report.txt -pl
