# Project1_Networks
## Code is in src directory
- ServerMulti.java is running in a linux server
- ClientMulti.java is running in a separate linux server that can communicate with ServerMulti.java (currently the communication configuration is hardcoded).
- Client will ask user to run a linux command and how many times(n) to run that linux command. n will become the number of client threads that are spawned and communicate with the single threaded server.
- The server runs that linux command and returns a result to each client thread.
