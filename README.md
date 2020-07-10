# Papeterie

## A propos de ce projet

### Cursus
ENI | Le développement en couches et la persistance des données avec Java SE  
~ [Module 3 - Développement de la couche BO]()  
~ [Module 4 - Développement de la couche DAL avec JDBC]()  


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

### Statut
Partie 1 - Terminé  
Partie 2 - En cours  

#### Dernière mise à jour
10/07/2020  
(README | 10/07/2020)