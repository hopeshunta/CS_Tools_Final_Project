# Laundry Tracker

Tracks when to do laundry next and how long it has been since you have done laundry. 

## The Problem

I keep forgetting when the last time I did laundry was and when the last time I washed my sheets was, so I want to build a program to help me keep track of how many days it has been. This will help me do laundry regularly and also not too often.

## How It Works

A java program that keeps track of how many days it has been since I did 
laundry gathering information and printing information using the terminal. It will read an input from the user, 
and then it will store that input as the date laundry was done. Then it will calculate how many days since laundry and the predicted date 
of when you should do laundry.

![System diagram](images/ToolsLaundryDiagram.drawio.png)

## Tech Stack

- **Language:** Java
- **Libraries:**: My project will use the java.io import and the java.time
                  import in order to keep track of the day and in order to save and retrieve what those days are.  


## Setup

Step-by-step: how does someone who just cloned your repo get it
running?

```bash
git clone https://github.com/your-username/laundry-tracker
cd laundry-tracker
#Compile the tracker
javac LaundryTracker.java
#Run the tracker
java LaundryTracker
```

## Usage
Once you have run the program decide whether you have done laundry today or not. 
Based on your response the terminal will print out how many days it has been since your last laundry day and when you should have your next laundry day.

```
