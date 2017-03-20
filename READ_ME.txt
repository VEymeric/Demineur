JAVA 

javac.nomdufichier.class ( compiler ) 
java.nomdufichier ( executer )

REGLES :
-Tous vos noms de classes doivent commencer par une majuscule ;
-Tous vos noms de variables doivent commencer par une minuscule ;
-Si le nom d'une variable est composé de plusieurs mots, le premier commence par une minuscule, le ou les autres par une majuscule, et ce, sans séparation ;
-Tout ceci sans accentuation !

FONCTION "UTILE" :
String : chaine.toLowerCase() : mettre en minuscule
	 chaine.toUpperCase() : mettre en majuscule
	 chaine1.equals(chaine2) : chaine2 = chaine1 ?
	 chaine.substring(int,int) : extraire une partie d'un string

NOTE :
STATIC : non instancier par une instance 
this() :  appel a un constructeur sans paramettre ( toujours mettre en premier ) 
Si des variable n'ont pas de public. il est mis instantanement en package ( mais on ne peut pas le definir de base)
Si une variable est mis en final et que c'est le constructeur par defaut ca ne marchera pas l'utilisateur doit forement entre une valeur par defaut !



AFFICHER : 
System.out.println("Hello world");
si rajout de variable mettre un + après


VARIABLE : 
int;float;double... écriture identique, déclaration de variable également identique 
		-> int one = 2;
		-> float one= 2.1,two=3.5;
		...

Déclarer une variable en string : String phrase = newString("ma Phrase");

OPÉRATION : 
Les opérations arithmétiques sont identiques ainsi que les incrementations.
Les convertions ne se font pas automatiquements, il faut le "forcer"
Les opérations sur avec des types differents : 

. EXEMPLE :
float b=10,c=3;
int a = b /c 
	-> ERREUR : int a= (int)(b/c);

float i = 1.23;
int k = i;
	-> ERREUR : int k = (int)i;

int d = 3,e=2;
float f = d/e
	-> ERREUR : nous perdons la virgule, il faut forcer de base les variables : float f = (float)d /(float)e;

CAS PARTICULIER : avec les String
	-de nimporte quoi à String  
		int i = 12;
		String j = new String();
		j = String.valueOf(i);

	-de String à nimporte quoi 
		String j = new String("12");
		int k = Integer.valueOff(j).intValue();

// intValue() peut etre remplacer par floatValue()...

FORMATAGE DES NOMBRES : 
On peut mettre des _ entre les nombres pour mieux visualisé les nombres.
Par contre il ne doit pas etre au tout début, à la toute fin ou à coté d'un point. Mais il peut etre à coté d'un autre _.


LES ENTRÉES CLAVIERS : 
On indique au programme qu'il va devoir lire l'entrée clavier, celui ci stanfar est System.in, avant d'indiquer qu'il faut lire nous devons instancier un objet Scanner:	 Scanner sc = new Scanner(System.in)

Ne pas oublier d'inclure le package du scanner (click droit 'Import')
import java.util.Scanner ou import java.util.* ( pour tout inclure  /!\ rend le code lent en plus, il peut il  avoir des conflits de nom )
Une fois le scanner déclarer nous créons une variable 
string str = sc.nextLine(); 

On peut également se restreindre au autre type avec comme forme : next<Type de la variable commençant par une majuscule>
/!\ ca ne fonctionne pas avec les char

Un problème est rencontré lorsque l'on met nextInt() suivit après par nextLine(), nous devons clear le scanner puis mettre la nouvelle valeur :  variable.nextLine();

LES CONDITIONS :
Les opérateurs logiques sont identiques au C++ 
Il y a juste "?:" qui permet de mettre une condition spéciale en affectation, 
Explication avec un exemple : 
	-EXEMPLE
		int x = 10, y = 20;
		int max = (x < y) ? y : x ; //Maintenant, max vaut 20
	-ETAPES
		Nous cherchons à affecter une valeur à notre variable max, mais de l'autre 			côté de l'opérateur d'affectation se trouve une condition ternaire…
		Ce qui se trouve entre les parenthèses est évalué : x est-il plus petit que 			y ? Donc, deux cas de figure se profilent à l'horizon :
			Si la condition renvoie true (vrai), qu'elle est vérifiée, la valeur 				qui se trouve après le ? sera affectée ;
			Sinon, la valeur se trouvant après le symbole: sera affectée.
		L'affectation est effective : vous pouvez utiliser votre variable max.



Les conditions sont identiques aux C++, 

if(){
	//ce qu'il faut faire
}
esle{
	//ce qu'il faut faire
}


switch(Variable){
	case nb:
		// ce qu'il faut faire ;
	break;
	case nb2:
		// ce qu'il faut faire ;
	break;
	...
	default:
		// ce qu'il faut faire ;	
}


for(/**condition**/){
	// ce qu'il faut faire ;
}

while (/**condition**/){
	// ce qu'il faut faire ;
}

LES TABLEAUX :
Déclarer un tableau : <type du tableau> <nom du tableau> [] = { <contenu du tableau>};
Déclarer un tableau vide : <type du tableau> <nom du tableau> [] = new <type>[taille];

Taille d'un tableau : <nom du tableau>.length;

LES CLASSES : 
Créer une fonction : public static <type de retour> <nom de la fonction> (<variable>)
			return (<type de retour>) <variable>;
Un fichier defini une classe => une seule classe par fichier ! 
privée ou public : visible dans tout les package ou pas visible de l'exterieur.
99,9% des cas public

a l'interieur :
private/pulic/protected : accebilité
static : attribut pas lié a une instance de la classe. Un instance est crée par un constructeur 
final : une fois modifié ne plus etre modifié

CONSTRUCTEUR :
Creer un constructeur ( ajout d'une classe ) et mettre le constructeur par defaut avec le meme nom que la classe.
Création d'instance d'objet ( ne pas oublier de marque new ) 

Pour importer sa classe au main il faut juste que si la nouvelle classe est déjà dans le même Package 

Le constructeur par défaut est invisible. Nous n'avons pas besoin de le définir. Un instance sera quand meme engendré

Il faut crée un constructeur par defaut et un avec les paramettres.
/!\ : De preference il faut mettre les variables en private afin d'éviter que d'autres programmes interviennent sur la classe.
Du coup on crée des accesseur et des mutateur. Un accesseur( get ) est une méthode qui va nous permettre d'accéder aux variables de nos objets en lecture, et un mutateur ( set ) nous permettra d'en faire de même en écriture ! Grâce aux accesseurs, vous pourrez afficher les variables de vos objets, et grâce aux mutateurs, vous pourrez les modifier.

Une classe permet de définir des objets. Ceux-ci ont des attributs (variables d'instance) et des méthodes (méthodes d’instance + accesseurs).
Les objets permettent d'encapsuler du code et des données.
Le ou les constructeurs d'une classe doivent porter le même nom que la classe et n'ont pas de type de retour.
L'ordre des paramètres passés dans le constructeur doit être respecté.
Il est recommandé de déclarer ses variables d'instance private, pour les protéger d'une mauvaise utilisation par le programmeur.
On crée des accesseurs et mutateurs (méthodes getters et setters) pour permettre une modification sûre des variables d'instance.
Dans une classe, on accède aux variables de celle-ci grâce au mot clé this.
Une variable de classe est une variable devant être déclarée static.
Les méthodes n'utilisant que des variables de classe doivent elles aussi être déclarées static.
On instancie un nouvel objet grâce au mot clé new.

HÉRITAGE :
Noté extends afin de rendre une classe associé a une autre.

Difference une un objet =  antité logique avec une classe =  déscription de l'objet ( technique/pratique)

Pour acceder à une fonction qui vient du parent il faut faire un appelle de super.(nom de la fonction)


METHODE : 
une methode est une primitive qui va pouvoir etre appeler sur une instance de la classe
Les methodes sont définie "comme" pour les classes. Sans oublié le retour, les attributs et l'interieur

ELLIPSIS (...) : 
mettre ... après un type permet de spécifié qu'il y a un grand nombre d'argument mais on ne connait pas le nombre adéquat. L'attribut sera un tableau qui s'utilise comme les autres tableaux
/!\ -> il faut mettre l'ellipsis qui doit etre le dernier argument de la fonction

GARBAGE COLLECTION : ( destructeur ) 
Pas utile en Java, car il y a un " eboueur de donné" il identifie l'objet utilisé et detecte celui qui est utilisé. Celle qui n'est plus utilisé sera supprimé

finalize() : pas synchrone ( on revoit ca plus tard )

ENUMERATIONS /!\: 
Une enumeration est un ensemble fini de valeur, on peut se permettre de la définir avec : public enum <className>{}
pour y acceder on aura juste besoin de faire <className>.<values>

Le constructeur est private ! 


DESIGN PATERN SIGNLETON: ( patron de conception ) 
Permet de toujours travailler sur la meme instance de l'objet

UP/DOWN CASTING
Regle du downcasting : 

LES EXEPTIONS :
Represente des situations qui peuvent etre réglé 
Unchecked ->
Checked ->

ABSTRAIT
pas de new avec une classe abstraite

INTERFACE :
remplace le mot class,
permet de donné des caracteristique suplementaire a une classe 











