class Animal:

	def __init__(self, nom, age):
		self.nom = nom
		self.age = age

	def manger(self, aliment):
		print(f"{self.nom} mange {aliment}")

	def dormir(self):
		print(f"zzzz")


class Chien:

	def __init__(self, nom, age):
		self._nom = nom
		self._age = age

	def __eq__(self, other: 'Chien'):
		return self._nom, self._age == other._nom, other._age

	def __gt__(self, other: 'Chien'):
		return self._age > other._age

	def __ge__(self, other: 'Chien'):
		return self._age >= other._age

	def _get_age(self):
		return self._age, self._age * 7

	def _set_age(self, age):
		self._age = age

	def _del_age(self):
		del self._age

	nom = property(lambda self: self._nom)
	age = property(_get_age, _set_age, _del_age)


if __name__ == '__main__':

	c1 = Chien('Toto', 4)
	print(c1.nom)
	print(c1.age)
