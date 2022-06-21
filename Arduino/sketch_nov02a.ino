void setup()
{
  Serial.begin(9600);
  pinMode(10, OUTPUT);
  pinMode(13, OUTPUT);
  pinMode(4, INPUT);   
}
 
void loop()
{  
  int motion_pin = digitalRead(4);
  int iData = 0;
  
  if(motion_pin == LOW)  
  {       
     Serial.println("0");
  }
  else 
  {
    Serial.println("1");
  }

  iData = Serial.read();
  if(iData != -1){
    if(iData == 0){
      digitalWrite(10, HIGH);
      digitalWrite(13, LOW);
    } else if(iData == 1){
      digitalWrite(10, HIGH);
      digitalWrite(13, LOW);
    }
  }
  delay(500);
}
