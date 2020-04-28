Command line instructions

Git global setup
git config --global user.name "Krishna Kumar S"
git config --global user.email "krishnakumar.s9@cognizant.com"

Create a new repository
git clone https://code.cognizant.com/834849/capestone.git
cd capestone
touch README.md
git add README.md
git commit -m "add README"
git push -u origin master

Existing folder
cd existing_folder
git init
git remote add origin https://code.cognizant.com/834849/capestone.git
git add .
git commit -m "Initial commit"
git push -u origin master

Existing Git repository
cd existing_repo
git remote add origin https://code.cognizant.com/834849/capestone.git
git push -u origin --all
git push -u origin --tags