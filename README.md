# Project1_Networks
## Code is in src directory
- ServerMulti.java is running in a linux server
- ClientMulti.java is running in a separate linux server that can communicate with ServerMulti.java (user inputs port and ip address of host to connect to(which will be the linux server that ServerMulti.java is running in)).
- Client will ask user to run a linux command and how many times(n) to run that linux command. n will become the number of client threads that are spawned and communicate with the multi-threaded server.
- The server runs that linux command and returns a result to each client thread.
