# Papeterie

## A propos de ce projet

### Cursus
ENI | Le développement en couches et la persistance des données avec Java SE  
~ [Module 3 - Développement de la couche BO](https://github.com/Dyrits/PAPETERIE/tree/master/Module%2003%20-%20Enonc%C3%A9%20TP%20-%20Papeterie%20-%20Partie%201)  
~ [Module 4 - Développement de la couche DAL avec JDBC](https://github.com/Dyrits/PAPETERIE/tree/master/Module%2004%20-%20Enonc%C3%A9s%20TP%20-%20Papeterie)  
~ [Module 5 - Développement de la couche BLL](https://github.com/Dyrits/PAPETERIE/tree/master/Module%2005%20-%20Enonc%C3%A9%20TP%20-%20Papeterie%20-%20Partie%204)  
~ [Module 6 - Couche IHM avec Swing](https://github.com/Dyrits/PAPETERIE/tree/master/Module%2006%20-%20Enonc%C3%A9s%20TP%20-%20Papeterie)
~ [Module 7 - Notions d'architecture MVC]()

### Énoncés (par l'ENI)

<details markdown="block">
<summary>Partie 1</summary>  

- Créer le projet papeterie avec Eclipse.
- Créer le package fr.eni.papeterie.bo.
- Créer les classes Article, Ramette, Stylo, Ligne et Panier en s'aidant du diagramme de classe fourni.
- Vérifier le fonctionnement des classes avec le composant AppliTestBO fourni.

</details>
<details markdown="block">
<summary>Partie 2</summary>  

- Sous SQL Server, créer une base de données et la nommer PAPETERIE_DB.
- Créer la table Articles à l’aide du fichier script.sql fourni.
- Dans le projet papeterie, implémenter la classe fr.eni.papeterie.dal.jdbc.ArticleDaoJdbcImpl pour que les méthodes suivantes soient fonctionnelles :
    - selectById : sélectionne un article par son identifiant
    - selectAll : sélectionne tous les articles de la table Articles
    - update : modifie les attributs d’un article
    - insert : ajoute un article en base de données et affecte à l'article l’identifiant créé par la base de données
    - delete : supprime un article en base de données
- Vérifier votre solution avec la classe fr.eni.papeterie.dal.AppliTestDAL fournie.

</details>
<details markdown="block">
<summary>Partie 3</summary> 

- Créer l’interface ArticleDAO et définir les méthodes :
    - selectById
    - selectAll
    - update
    - insert
    - delete
- Utiliser l’interface ArticleDAO dans ArticleDAOJdbcImpl.
- Externaliser la configuration de l’accès aux données en implémentant les classes :
    - Settings
    - JdbcTools
- Implémenter la classe DAOFactory.
- Vérifier votre solution avec la classe fr.eni.papeterie.dal.AppliTestDAL fournie.

</details>
<details markdown="block">
<summary>Partie 4</summary> 

- À l’aide du diagramme de classes fourni, implémenter la couche BLL pour que les exigences suivantes soient respectées :
    - Les attributs des articles sont obligatoires.
    - Le grammage pour une ramette et la quantité en stock attendent des valeurs positives.
    - Un catalogue est une liste d’articles gérée en mémoire et extraite à partir d’une source de données.

</details>
<details markdown="block">
<summary>Partie 5</summary> 

Réaliser l’écran article ci-dessous :  

> Plus de détails au sein du fichier PDF correspondant.

L’écran doit permettre :
- De naviguer vers l’article précédent dans le catalogue :
    - La sélection du type est inactive dans le cadre de la navigation entre articles.
    - La sélection du grammage est inactive pour un stylo.
    - La sélection de la couleur est inactive pour une ramette.
- De créer un nouvel article :
    - La sélection du type Stylo ou Ramette se fait via des boutons radio.
    - Selon le type, rendre actif ou inactif la saisie du grammage et de la couleur.Le développement en couches et la persistance des données avec Java SE
- D’enregistrer les modifications d’un article existant ou d’un nouvel article.
    - De supprimer l’article courant.
    - De naviguer vers l’article suivant dans le catalogue

</details>
<details markdown="block">
<summary>Partie 6</summary> 

Réaliser l’écran catalogue ci-dessous :

> Plus de détails au sein du fichier PDF correspondant.

L’écran doit respecter les exigences suivantes :
- Lister tous les articles enregistrés dans le catalogue.
- Permettre l’utilisation d’un ascenseur vertical si la taille de l’écran n’est pas suffisante pour afficher tous les articles.
- Interdire la modification des articles dans le tableau.
- La première colonne affiche l’image d’un stylo ou l’image d’une ramette selon le type de l’article

</details>
<details markdown="block">
<summary>Partie 7</summary> 

- Créer un composant barre de boutons réutilisable.
- Utiliser ce composant dans l’écran détail article.

</details>

> Plus de détails sont disponibles dans les fichiers au format PDF correspondant à chaque énoncé.

### Technologie principale
- Java

### Détails | Commentaires
Ce projet a été construit à partir de zéro en suivant des instructions spécifiques.   

<details markdown="block">
<summary>Partie 1</summary> 

Le fichier AppliTestBo.java a été fourni avec l'énoncé.  
En plus des instructions de base, la gestion des variations de stock pour chaque article et du montant du panier ont été ajoutés.

</details>
<details markdown="block">
<summary>Partie 2</summary> 

Les fichiers DALException.java et AppliTestDAL.java ont été fournis avec l'énoncé ainsi que la requête SQL permettant de générer la table `Articles`.

</details>
<details markdown="block">
<summary>Partie 3</summary> 

Le fichier AppliTestDAL.java a été fourni avec l'énoncé.

</details>
<details markdown="block">
<summary>Partie 4</summary> 

Le fichier AppliTestDLL.java a été fourni avec l'énoncé.  
En plus des instructions de base, une gestion des exceptions a été ajoutée au sein du package BO.

</details>
<details markdown="block">
<summary>Partie 5</summary> 

Les ressources (icônes) ont été fournies avec l'énoncé.

</details>
<details markdown="block">
<summary>Partie 6</summary> 

Les ressources (icônes) ont été fournies avec l'énoncé.

</details>
<details markdown="block">
<summary>Partie 7</summary> 

Les différents classe présentes dans le diagramme de classes ont été implémentées, mais des noms différents (en anglais) ont été utilisées.

</details>

### Statut
Partie 1 - Terminée  
Partie 2 - Terminée   
Partie 3 - Terminée  
Partie 4 - Terminée  
Partie 5 - Terminée  
Partie 6 - Terminée  
Partie 7 - Terminée  

#### Dernière mise à jour
14/07/2020  
(README | 14/07/2020)