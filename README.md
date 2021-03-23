# TigerFareCalculator

## Problem Statement 

The transport officials from the city of Nepu want to design a payment system for public metro transport. They have come up with the idea of a prepaid card - TigerCard - which is an NFC enabled card that is to be tapped at entry and exit points of the metro stations.  

To make the fares easier to understand for the commuters, the city has been divided into zones. Zone 1 is the central area and Zone 2 forms a concentric ring around Zone 1. Each metro station has been assigned to a zone. In the future, more zones will be added as the metro expands.  The problem statement is to design the fare calculation engine for TigerCard. Below are the definitions and fare rules. Rules Time of travel The fare varies based on the time of the travel. There are two types of fares based on time of travel: 

● Peak hours timings<br />
  ○ Monday - Friday   ■ 07:00 - 10:30, 17:00 - 20:00 <br />
  ○ Saturday - Sunday ■ 09:00 - 11:00, 18:00 - 22:00 <br />
 ● Off-peak hours timings <br />
  ○ All hours except the above peak hours <br />
  ○ If travelling from any station outside Zone 1 to a station in Zone 1 on the below times <br />
    ■ Monday - Friday   ● 17:00  - 20:00 <br />
    ■ Saturday - Sunday ● 18:00 - 22:00  <br />
    
The below table shows the fare that commuters will have to pay for a single journey from station A to station B. There is no concept of a round-trip journey. 


Screenshot 2021-03-24 at 5.06.41 AM![image](https://user-images.githubusercontent.com/13884202/112232563-d13a3780-8c5e-11eb-8be6-b7e839103aa5.png)

#### Fare Capping
Fare capping works by rewarding commuters with free rides after they meet the fare equivalentof a daily, weekly, or monthly pass. With fare capping, social equity is achieved by removing upfront cost barriers associated with the recurrent passes. For example, a single ride costs 30 and the daily pass costs 90, the commuter earns a daily pass after the first 3 rides. For the rest of the day, all rides will be free for the commuter. Due to fare capping, the commuter does not have to invest 90 on the daily pass as they get the same features using a regular card.

The following capping categories are available:<br />
  ● Daily<br />
  ● Weekly<br />
  
Capping Limits:

Screenshot 2021-03-24 at 5.10.04 AM![image](https://user-images.githubusercontent.com/13884202/112232836-4c035280-8c5f-11eb-92ae-acbaa2bfeea2.png)

The cap that is applicable for a day is based on the farthest journey in a day. For example, if a few journeys are within zone 1 and a single journey is between zone 1 & 2, then the daily cap applicable will be the one for zone 1 - 2. The first example later in the document illustrates the same. The weekly cap uses the same logic as the daily cap when determining the zones applicable for the week.  The weekly cap works in conjunction with the daily cap. So, when computing the weekly cap, each day fare might still be capped to that day’s daily cap. The second example later in the document illustrates the same. 
