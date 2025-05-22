1) git init


2) 
echo "Hello world" > fichier.txt
git add fichier.txt
git commit -m "C1 - Ajout du fichier"


3) 
modification de fichier.txt
git add fichier.txt
git commit -m "C2 - modification de fichier.txt"


4) 
git log et prendre le hash de C1
git checkout C1
git checkout -b B1


5) 
modification de fichier.txt
git add fichier.txt
git commit -m "C3 - modification de fichier.txt dans branche B1 "


6) 
git checkout master
git merge B1