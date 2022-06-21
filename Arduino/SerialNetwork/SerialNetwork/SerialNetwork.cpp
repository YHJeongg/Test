//#define WIN#@_LEAN_AND_MEAN

#include <windows.h>
#include "./SerialPort.h"
#include <stdlib.h>
#include <stdio.h>

#define MAX_COUNT 10
#define SERVER_IP "168.126.146.39"
#define SERVER_PORT 8080

#pragma comment(lib, "ws2_32.lib")
int SendData(int val)
{
	WSADATA wsa;
	SOCKET s;
	struct sockaddr_in server;
	char message[1024];
	char server_reply[2000];
	int recv_size;
	int int_return = -1;

	printf("\nlnitialising Winsock...");
	if(WSAStartup(MAKEWORD(2,2),&wsa) != 0)
	{
		printf("Failed. Error Code : %d", WSAGetLastError());
		return 1;
	}

	printf("lnitialised.\n");

	if((s = socket(AF_INET , SOCK_STREAM, 0)) == INVALID_SOCKET)
	{
		printf("Could not create socket : %d" , WSAGetLastError());
	}

	printf("Socket created.\n");

	server.sin_addr.s_addr = inet_addr(SERVER_IP);
	server.sin_family = AF_INET;
	server.sin_port = htons(SERVER_PORT);

	if(connect(s, (struct sockaddr *)&server , sizeof(server)) < 0)
	{
		puts("connect error");
		return 1;
	}

	puts("Connected");

	sprintf(message,"GET /20142512/server.jsp?msg=%d HTTP/1.1\r\nHost: %s:%d\r\n\r\n", val,SERVER_IP,SERVER_PORT);
	if(send(s, message, strlen(message), 0 ) < 0)
	{
		puts("Send failed");
		return 1;
	}
	puts("Data Send\n");
	if ((recv_size = recv(s , server_reply , 2000, 0)) != SOCKET_ERROR)
    {
	 server_reply[recv_size] = '\0';
	 printf("Server RECV:%s\n", server_reply);
	 if(strstr(server_reply, "OK:48")) {
			int_return = 0;
			return 0;
		} else if(strstr(server_reply, "OK:49")) {
			int_return = 1;
			return 1;
		} else { int_return = 3;
		}
    }

 if(strstr(server_reply, "\r\n\r\nOK") == NULL)
 {
	if((recv_size = recv(s , server_reply , 2000 , 0)) != SOCKET_ERROR)
	{
		server_reply[recv_size] = '\0';
		printf("Server RECVVV:%s\n", server_reply);

		if(strstr(server_reply, "OK:48")) {
			int_return = 0;
			return 0;
		} else if(strstr(server_reply, "OK:49")) {
			int_return = 1;
			return 1;
		} else { int_return = 3;
		}
	}
 }
closesocket(s);
WSACleanup();

return int_return;

}

int main(int argc, char* argvp[])
{
	CSerialPort _serial;
	char toggle = 0;
	int count = 0;
	if(_serial.OpenPort("COM4"))
	{
		_serial.ConfigurePort(CBR_9600, 8, FALSE, NOPARITY, ONESTOPBIT);

		_serial.SetCommunicationTimeouts(0, 0, 0, 0, 0);		

		while(1)
		{
			int return_Value = -1;
			BYTE* pByte = new BYTE[1];

			if (_serial.ReadByte(pByte, 1))
			{
				char cVal = '0';
				memcpy(&cVal, pByte, 1);
				return_Value = SendData((int)cVal);
			}

			delete [] pByte;
			if(return_Value == 1) { toggle = 1;
			} else {				toggle = 0; }
			_serial.WriteByte( (BYTE*)&toggle, sizeof(toggle));

		}
	}
	else {
		printf("[Port Open Fail\n");
	}

	_serial.ClosePort();
	return 0;
}