from functools import wraps


def controle_types(ancienne_fonction):

	@wraps(ancienne_fonction)
	def nouvelle_fonction(*args):

		fonction_annotations = ancienne_fonction.__annotations__.copy()
		type_retour = fonction_annotations.pop('return', type(None))

		# Gérer le cas de None
		if type_retour is None:
			type_retour = type(None)

		# Vérification des types des arguments
		types_attendus = tuple(fonction_annotations.values())
		types_obtenus = tuple([type(a) for a in args])
		if types_attendus != types_obtenus:
			raise TypeError(
				f"Les types ne correspondent pas (attendu : {[t.__name__ for t in types_attendus]}, "
				f"obtenu : {[t.__name__ for t in types_obtenus]})"
			)

		# Exécution de la fonction
		retour = ancienne_fonction(*args)

		# Vérification du type de retour
		if type(retour) != type_retour:
			raise TypeError(
				f"Les types de retour ne correspondent pas (attendu : {type_retour}, "
				f"obtenu : {type(retour)})"
			)
		return retour

	return nouvelle_fonction


@controle_types
def addition(a: int, b: float) -> float:
	return a + b


@controle_types
def concatenation(a: str, b: str) -> str:
	return a + b


@controle_types
def fonction_qui_retourne_rien() -> None:
	pass


if __name__ == '__main__':

	print(addition(4, 4.2))
	print(concatenation('toto', 'tata'))
	fonction_qui_retourne_rien()