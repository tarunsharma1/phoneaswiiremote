phoneaswiiremote
================

Use your phone as a wii remote to play pc games.Accelerometer values from a windows phone are sent to a server build using nodejs and websockets .The server then sends the data to a java class which uses websockets in java and java.Robot class to perform approriate actions like keyPress
Windows phone uses WebSocket4Net which can be downloaded using the NuGet package managaer in visual studio.
For webscokets in java i have used TooTallNates java websocket library on eclipse.
NodeJS is to be first downloaded and then web_sockets is to be downloaded using nodepackagemanager(npm) .The Node server is written in javascript and can be run on command prompt.
On the recieving end , i.e on the computer the game is runnning , the values recieved from the java websockets are checked and appropriate key is pressed using the java.Robot class which is available by default with jdk.
