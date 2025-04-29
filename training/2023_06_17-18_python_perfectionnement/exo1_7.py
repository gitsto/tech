from functools import wraps


def controle_types(*types_attendus):

	def decorateur(ancienne_fonction):

		@wraps(ancienne_fonction)
		def nouvelle_fonction(*args):
			types_obtenus = tuple([type(a) for a in args])
			if types_attendus != types_obtenus:
				raise TypeError(
					f"Les types ne correspondent pas (attendu : {[t.__name__ for t in types_attendus]}, "
					f"obtenu : {[t.__name__ for t in types_obtenus]})"
				)
			return ancienne_fonction(*args)

		return nouvelle_fonction

	return decorateur


@controle_types(int, int)
def addition(a, b):
	return a + b


@controle_types(str, str)
def concatenation(a, b):
	return a + b


if __name__ == '__main__':

	print(addition(4, 4))
	print(concatenation('toto', 'tata'))
