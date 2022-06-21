import paramiko
import openpyxl
import time

username = input("account: ")
password = input("password: ")
number = int(input("number of devices: "))

file = openpyxl.load_workbook('C:\\Users\\GT\\Desktop\\IPLIST\\ip_list.xlsx')
sheet = file['ip']

try:
    for i in range(number):
        a = sheet.cell(row=i+1, column=1).value
        session = paramiko.SSHClient()
        session.set_missing_host_key_policy(paramiko.AutoAddPolicy())
        session.connect(a, 22, username=username, password=password)
        print("login success")
        try:
            conn = session.open_sftp()
            conn.put("C:\\Users\\GT\\Desktop\\OS\\EOS-2GB-4.24.5M.swi", "/mnt/flash/EOS-2GB-4.24.5M.swi")
            print("file upload success")
            session.exec_command("install source flash:/EOS-2GB-4.24.5M.swi")
            print("{0} ==> os install success".format(a))
            time.sleep(10)
            session.exec_command("reload now")
            print("{0} ==> reload success".format(a))
            conn.close()
            session.close()
        except:
            print("please check again, file name or directory name")
except:
    print("please check again, ip and account information")