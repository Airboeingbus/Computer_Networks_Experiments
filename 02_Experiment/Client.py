import socket

client_socket = socket.socket()
host = '127.0.0.1'
port = 12345

client_socket.connect((host, port))
client_socket.send("Hello from Workstation X".encode())

ack = client_socket.recv(1024).decode()
print(f"Acknowledgment from server: {ack}")

client_socket.close()
