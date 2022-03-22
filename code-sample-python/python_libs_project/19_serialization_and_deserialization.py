import json
import pickle
import pickletools
import time

if __name__ == '__main__':
    print("serialization and deserialization starting")

    #  pickle

    entry = {}  # ②
    entry['title'] = 'Dive into history, 2009 edition'
    entry['article_link'] = 'http://diveintomark.org/archives/2009/03/27/dive-into-history-2009-edition'
    entry['comments_link'] = None
    entry['internal_id'] = b'\xDE\xD5\xB4\xF8'
    entry['tags'] = ('diveintopython', 'docbook', 'html')
    entry['published'] = True

    entry['published_date'] = time.strptime('Fri Mar 27 22:20:42 2009')  # ③
    print(entry)

    #  file is saved
    with open('entry.pickle', 'wb') as f:
        pickle.dump(entry, f)

    #  file is read again
    with open('entry.pickle', 'rb') as f:
        entry_after_read = pickle.load(f)
        print(entry == entry_after_read)

    print("****")

    # you can also "debug" a file
    with open('entry.pickle', 'rb') as f:
        pickletools.dis(f)

    print("****")

    #  file is not required, you can dump data as bytes
    entry_dumped = pickle.dumps(entry)  # ①
    print(type(entry_dumped))

    entry_reread = pickle.loads(entry_dumped)  # ③
    print(entry_reread == entry)

    print("****")

    # json

    basic_entry = {}  # ①
    basic_entry['id'] = 256
    basic_entry['title'] = 'Dive into history, 2009 edition'
    basic_entry['tags'] = ['diveintopython', 'docbook', 'html']
    basic_entry['published'] = True
    basic_entry['comments_link'] = None
    print(f"basic: {basic_entry}")

    serialized_json = json.dumps(basic_entry)
    print(f"serialized: {serialized_json}")

    deserialized_entry = json.loads(serialized_json)
    print(f"deserialized: {deserialized_entry}")
    print(basic_entry == deserialized_entry)

