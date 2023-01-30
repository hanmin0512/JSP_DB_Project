import socket
import sys
client  = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

client.connect(("172.31.40.246",1234))

while 1:
    try:
        msg = input()
        msg = msg.encode('utf-8')
    except KeyboardInterrupt:
        sys.exit(0)

    client.send(msg)

    data = client.recv(1024)
    if data == 'exit()':
        break
    print(data)
client.close()