import binascii
import hashlib
from Cryptodome.Cipher import DES, PKCS1_OAEP, AES
from Cryptodome.PublicKey import RSA
from Cryptodome.Random import get_random_bytes
from cryptography.fernet import Fernet


if __name__ == '__main__':
    print("hashing and encryption starting")

    #  md5
    hashed_string = "Python rocks!"
    ascii_string = hashed_string.encode("ascii")
    md5 = hashlib.md5()
    md5.update(ascii_string)
    print(f"md5: '{md5.digest()}'")
    print(f"md5_hex: '{md5.hexdigest()}'")

    # sha1
    print(f"sha1: '{hashlib.sha1(ascii_string).hexdigest()}'")

    # sha256 pbkdf2 hmac signature
    dk = hashlib.pbkdf2_hmac(hash_name='sha256',
                             password=b'bad_password34',
                             salt=b'bad_salt',
                             iterations=100000)
    print(f"pbkdf2 hmac sha256: '{binascii.hexlify(dk)}'")
    print("****")

    #  pip install pycryptodome
    # DES
    key = b'abcdefgh'


    def pad(text):
        while len(text) % 8 != 0:
            text += b' '
        return text


    des = DES.new(key, DES.MODE_ECB)
    text = b'Python rocks!'
    padded_text = pad(text)

    encrypted_text = des.encrypt(padded_text)
    print(encrypted_text)
    print(des.decrypt(encrypted_text))
    print("****")

    # RSA
    code = 'nooneknows'
    key = RSA.generate(2048)
    encrypted_key = key.exportKey(passphrase=code, pkcs=8,
                                  protection="scryptAndAES128-CBC")
    with open('my_private_rsa_key.bin', 'wb') as f:
        f.write(encrypted_key)
    with open('my_rsa_public.pem', 'wb') as f:
        f.write(key.publickey().exportKey())

    # encrypt and decrypt a file
    text_to_save = b'blah blah blah Python blah blah'
    with open('encrypted_data.bin', 'wb') as out_file:
        recipient_key = RSA.import_key(
            open('my_rsa_public.pem').read())
        session_key = get_random_bytes(16)

        cipher_rsa = PKCS1_OAEP.new(recipient_key)
        out_file.write(cipher_rsa.encrypt(session_key))

        cipher_aes = AES.new(session_key, AES.MODE_EAX)
        data = text_to_save
        ciphertext, tag = cipher_aes.encrypt_and_digest(data)
        print(ciphertext)

        out_file.write(cipher_aes.nonce)
        out_file.write(tag)
        out_file.write(ciphertext)

    with open('encrypted_data.bin', 'rb') as fobj:
        private_key = RSA.import_key(
            open('my_private_rsa_key.bin').read(),
            passphrase=code)

        enc_session_key, nonce, tag, ciphertext = [fobj.read(x)
                                                   for x in (private_key.size_in_bytes(),
                                                             16, 16, -1)]

        cipher_rsa = PKCS1_OAEP.new(private_key)
        session_key = cipher_rsa.decrypt(enc_session_key)

        cipher_aes = AES.new(session_key, AES.MODE_EAX, nonce)
        data = cipher_aes.decrypt_and_verify(ciphertext, tag)
        print(data, data == text_to_save)

    print("****")

    # pip3 install cryptography
    cipher_key = Fernet.generate_key()
    print(cipher_key)

    cipher = Fernet(cipher_key)
    text = b'My super secret message'
    encrypted_text = cipher.encrypt(text)
    print(encrypted_text)
    decrypted_text = cipher.decrypt(encrypted_text)
    print(decrypted_text)