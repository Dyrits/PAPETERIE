# Papeterie

## A propos de ce projet

### Cursus
ENI | Le développement en couches et la persistance des données avec Java SE  
~ [Module 3 - Développement de la couche BO](https://github.com/Dyrits/PAPETERIE/tree/master/Module%2003%20-%20Enonc%C3%A9%20TP%20-%20Papeterie%20-%20Partie%201)  
~ [Module 4 - Développement de la couche DAL avec JDBC](https://github.com/Dyrits/PAPETERIE/tree/master/Module%2004%20-%20Enonc%C3%A9s%20TP%20-%20Papeterie)  
~ [Module 5 - Développement de la couche BLL](https://github.com/Dyrits/PAPETERIE/tree/master/Module%2005%20-%20Enonc%C3%A9%20TP%20-%20Papeterie%20-%20Partie%204)

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

### Statut
Partie 1 - Terminée  
Partie 2 - Terminée   
Partie 3 - Terminée

#### Dernière mise à jour
12/07/2020  
(README | 12/07/2020)