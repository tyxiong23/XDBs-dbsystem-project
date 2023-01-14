rm -r data/DATASET/
python3 main.py -f ../yanshou/jiaoben/create.sql

python3 main.py -f ../yanshou/data/part.csv -t PART DATASET
python3 main.py -f ../yanshou/data/region.csv -t REGION DATASET
python3 main.py -f ../yanshou/data/nation.csv -t NATION DATASET
python3 main.py -f ../yanshou/data/customer.csv -t CUSTOMER DATASET
python3 main.py -f ../yanshou/data/supplier.csv -t SUPPLIER DATASET

python3 main.py -f ../yanshou/data/partsupp.csv -t PARTSUPP DATASET
python3 main.py -f ../yanshou/data/orders.csv -t ORDERS DATASET