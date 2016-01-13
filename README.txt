Bonjour,

Comme vous le constaterez le cloud n'est pas fini, et l'application ne sauvegarde pas avec le cloud.

Ceci est du au fait que la premiere partie nous a pris un temps fou, car nous y avons rencontrer d'enorme probleme, et apres avoir enfin reussi
a faire communiquer notre application avec le cloud, le temps imparti etait trop faible (notre groupe a eu pas mal de temps pris ces vacances pour raisons professionnel). 
Nous avons donc tente de coder un maximum, mais si ce n'est faire planter l'application nous ne sommes arrive a rien.
Nous avons fait un rollback a notre derniere version qui fonctionnait et qui, au niveau du cloud, se contente de communiquer avec lui en lui envoyant un "Hi Manfred".

Autre point, lorsque l'on build le module backend pour le lier à l'application, il crée des répertoire avec des chemins trop longs pour être compresser et envoyer sur git,
J'ai donc mis en ligne la version sans build. Il vous faudra procéder à la génération du build : Build -> Deploy Module to app Engine


Merci de votre comprehention.

Arnaud Manuel et Maximilien Borgeat