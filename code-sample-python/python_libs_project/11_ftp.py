from ftplib import FTP

if __name__ == '__main__':
    print("starting ftp")

    ftp = FTP('ftp.cse.buffalo.edu')
    login_result = ftp.login()
    print(login_result)

    list_result = ftp.retrlines('LIST')
    print(list_result)

    cwd_result = ftp.cwd('mirror')
    print(cwd_result)

    list_result = ftp.retrlines('LIST')
    print(list_result)

    print("****")

    # methods to retrieve and store lines or binaries
    # ftp.retrlines()
    # ftp.retrbinary()
    #  ftp.storlines()
    #  ftp.storbinary()
