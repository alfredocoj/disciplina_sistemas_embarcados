#include <SPI.h>
#include <Ethernet.h>
  
byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
byte ip[] = { 192, 168, 1, 4 };
     
EthernetServer server(80);
  
String readString;
int Pin = 7;
int Pin0 = 8;  
int Pin1 = 9;
void setup(){
  
  pinMode(Pin0, OUTPUT);
  pinMode(Pin1, OUTPUT);
  pinMode(Pin, OUTPUT);
  Ethernet.begin(mac, ip);
  server.begin();
}
  
void loop(){
  EthernetClient client = server.available();
  if (client) {
    while (client.connected()) {
      if (client.available()) {
        char c = client.read();
  
        if (readString.length() < 100) {
          readString += c;             
        }
 
        if (c == '\n') {
         client.println("<!DOCTYPE html>");
client.println("<html>");
client.println("<head>");
client.println("<meta charset='/'UTF-8'/'/>");
client.println("<title>Automação Residencial</title>");
client.println("<style>");
  client.println("  body{");
    client.println("  background-color: #f9f9f9;");
client.println("      font-family: RobotoDraft, 'Helvetica Neue', Helvetica, Arial;");
client.println("      -webkit-user-select: none;");
client.println("      -moz-user-select: none;");
client.println("      -ms-user-select: none;");
client.println("      -webkit-tap-highlight-color: rgba(0,0,0,0);");
client.println("      -webkit-touch-callout: none;");
client.println("    }");

client.println("    a{");
 client.println("        text-decoration: none;");
client.println("    }");
    
client.println("    .button {");
client.println("      display: inline-block;");
client.println("      position: relative;");
client.println("      width: 120px;");
client.println("      height: 32px;");
client.println("      line-height: 32px;");
client.println("      border-radius: 2px;");
client.println("      font-size: 0.9em;");
client.println("      background-color: #fff;");
client.println("      color: #646464;");
client.println("    }");
    
client.println("     .button > paper-ripple {");
client.println("      border-radius: 2px;");
client.println("      overflow: hidden;");
client.println("    }");
    
client.println("     .button.blue {");
client.println("      background-color: #4285f4;");
client.println("      color: #fff;");
client.println("    }");
    
client.println("    .button.green {");
  client.println("    background-color: #0f9d58;");
client.println("      color: #fff;");
client.println("    }");
    
client.println("    .button.raised {");
client.println("      transition: box-shadow 0.2s cubic-bezier(0.4, 0, 0.2, 1);");
client.println("      transition-delay: 0.2s;");
client.println("      box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.26);");
client.println("    }");
    
client.println("    .button.raised:active {");
client.println("      box-shadow: 0 8px 17px 0 rgba(0, 0, 0, 0.2);");
client.println("      transition-delay: 0s;");
client.println("    }");
    
client.println("    .center {");
client.println("      text-align: center;");
client.println("    }");
client.println("</style>");
client.println("</head>");
client.println("<body>");
client.println("    <h1>HI HOME</h1>");
    
client.println("    <div>");
client.println("        <h3>AR CONDICIONADO</h3>");
client.println("        <a class='button raised blue center' id='ligarAr' href='/ligarAr'>Ligar</a>");
client.println("        <a class='button raised green center' id='desligarAr' href='/desligarAr'>Desligar</a>");
client.println("    </div>");
    
client.println("    <div>");
client.println("        <h3>LÂMPADAS</h3>");
client.println("        <a class='button raised blue center' id='ligarLampada' href='/ligarLampada'>Ligar</a>");
client.println("        <a class='button raised green center' id='desligarLampada' href='/desligarLampada'>Desligar</a>");
client.println("    </div>");
    
client.println("    <div>");
client.println("        <h3>PORTÃO</h3>");
client.println("        <a class='button raised blue center' id='abrirPortao' href='/abrirPortao'>Abrir</a>");
client.println("        <a class='button raised green center' id='fecharPortao' href='/fecharPortao'>Fechar</a>");
client.println("    </div>");
client.println("</body>");
client.println("</html>");
           
          delay(1);
          client.stop();
           
          if(readString.indexOf("ligarLampada") > 0)
          {
            digitalWrite(Pin, HIGH);
          }
          else {
            if(readString.indexOf("desligarLampada") > 0)
            {
              digitalWrite(Pin, LOW);
            }
          }
           
          if(readString.indexOf("abrirPortao") > 0)
          {
            digitalWrite(Pin0, HIGH);
          }
          else {
            if(readString.indexOf("fecharPortao") > 0)
            {
              digitalWrite(Pin0, LOW);
            }
          }
           
          if(readString.indexOf("ligarAr") > 0)
          {
            digitalWrite(Pin1, HIGH);
          }
          else {
            if(readString.indexOf("desligarAr") > 0)
            {
              digitalWrite(Pin1, LOW);
            }
          }
          readString="";    
        }
      }
    }
  }
}
