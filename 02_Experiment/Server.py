import socket

server_socket = socket.socket()
host = '127.0.0.1'
port = 12345

server_socket.bind((host, port))
server_socket.listen(1)
print(f"Server listening on {host}:{port}")

conn, addr = server_socket.accept()
print(f"Connection from {addr} established")

message = conn.recv(1024).decode()
print(f"Received message from client: {message}")

conn.send("Message received".encode())
conn.close()
