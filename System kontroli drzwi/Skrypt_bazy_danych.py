from socket import *
import time

address = ('192.168.0.177', 5000)  # Adres arduino w sieci
client_socket = socket(AF_INET, SOCK_DGRAM)
client_socket.settimeout(1)
key = ["797ca3b2"]

while (1):
    try:
        client_socket.sendto("connect".encode(), address)
        rec_data, addr = client_socket.recvfrom(2048)
        time.sleep(1000)
        temp = str(rec_data.decode())
        print(f"Odebrałem takie dane: {temp}")
        with open("wejscia.txt", 'a') as file:
            open_file = True
            file.write(f"Nowe wejście karty: {temp}, o godzinie: {time.asctime()} - ")
            for i in key:
                if temp == i:
                    file.write("przyznano dostęp\n")
                    file.close()
                    open_file = False
                    client_socket.sendto("1".encode(), address)
            if open_file:
                file.write("nieprzyznano dostępu\n")
                file.close()
            client_socket.sendto("0".encode(), address)
    except:
        pass
