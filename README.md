### Specifications

| Method | Tools/FrameWork | Action |
|--------|-----------------|--------|
|Architectural Pattern| mvc       | [model](https://github.com/abdulmukit98/techshopJU/blob/orderPCB/orderPCB/app/src/main/java/edu/cseju/orderpcb/model/PCBDetails.java) |
|                     |   mvc     | [view](https://github.com/abdulmukit98/techshopJU/blob/orderPCB/orderPCB/app/src/main/res/layout/activity_main.xml)  |
|                     | mvc        | [controller](https://github.com/abdulmukit98/techshopJU/blob/orderPCB/orderPCB/app/src/main/java/edu/cseju/orderpcb/MainActivity.java) |
|Database             | Firebase   | [Schema](https://github.com/abdulmukit98/techshopJU/wiki/Database) |
|Documentation Tool   | Doxygen| |
|Unit Testing| JUnit4 | [test class](https://github.com/abdulmukit98/techshopJU/blob/orderPCB/orderPCB/app/src/test/java/edu/cseju/orderpcb/TestModel/TestClassTesting.java) |
|UI Testing| Espresso | [test class](https://github.com/abdulmukit98/techshopJU/blob/orderPCB/orderPCB/app/src/androidTest/java/edu/cseju/orderpcb/MainActivityTest.java) |
|Instrumented Testing | JUnit & Espresso | [test class](https://github.com/abdulmukit98/techshopJU/blob/orderPCB/orderPCB/app/src/androidTest/java/edu/cseju/orderpcb/MainActivityInstrumentedTest.java) (running)|

### User Interface
![ui](https://github.com/abdulmukit98/techshopJU/blob/main/images/ui/pcbUI.PNG)

### About
* User need to design his custom pcb using KiCad / Eagle 
* PCB Dimension musb be rectangular
* Length and Highth is maximum 12 inch
* Space between 2 track must be greater than 8 mils
* Green masking is free from charge
* Cost of PCB per square inch is 20 taka.
* If user generated multiple Gerber Files, they must be zipped in one file when upload
