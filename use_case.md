# Objectif du système :
La temperature de la pièce est compris entre 18°C et 24°C, avoir une pièce qui est suffisament éclairée et une fermeture des portes automatique.  
Si une personne quitte une salle le thermostat redescend pour ne pas chauffer la pièce pour rien.  
Les capteurs utilisé sont Thermostat, Présence, Photosenseurs.
# Use Case :

### Premier Use Case :

Contexte :
- Une personne rentre dans une pièce vide
- Pièce bien éclairée par la lumière exterieur
- Temperature de la pièce en dessous des 18°C

Action du systeme : 
- Allumer le radiateur pour passer la barre des 18°C
- Ferme la porte au bout de 5 sec

### Second Use Case : 
Contexte : 
- Une personne rentre dans une pièce vide 
- Pièce bien eclairée par la lumière externe
- Temperature pièce au dessus de 24°C

Action du systeme : 
- Ferme la porte au bout de 5 sec


### Troisième Use Case : 
Contexte : 
- Rentre dans une pièce vide 
- Pièce pas bien éclairée par la lumière extérieur
- Temperature pièce au dessus de 24°C

Action du systeme : 
- Ferme la porte au bout de 5 sec
- Allumer la lumière

### Quatrième Use Case : 
Contexte : 
- Une personne rentre dans une pièce occupé
- Pièce pas bien éclairé par la lumière éxterieur
- Temperature pièce au dessus de 24°C

Action du systeme : 
- Pas de changement d'état
- Ferme la porte au bout de 5 sec

### Cinquième Use Case : 
Contexte :
- Une personne sort d'une pièce occupé
- Pièce pas bien éclairé par la lumière éxterieur
- Temperature pièce au dessus de 24°C

Action du systeme : 
- Pas de changement d'état
- Ferme la porte au bout de 5 sec

### Sixième Use Case : 
Contexte : 
- Une personne sort d'une pièce 
- Pièce pas bien eclairée par la lumière éxterieur
- Temperature pièce au dessus des 24°C

Action du systeme : 
- Eteindre la lumière 
- Fermer la porte après 5 sec
- Baisser le thermostat

### Septième Use Case : 
Contexte : 
- Rentre dans une pièce vide 
- Pièce pas bien éclairée par la lumière extérieur
- Temperature pièce en dessous 18°C

Action du systeme : 
- Ferme la porte au bout de 5 sec
- Allumer la lumière
- Allumer le radiateur pour passer la barre des 18°C