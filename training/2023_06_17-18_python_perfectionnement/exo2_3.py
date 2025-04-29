class Animal:

	def __init__(self, nom, age):
		self.nom = nom
		self.age = age

	def manger(self, aliment):
		print(f"{self.nom} mange {aliment}")

	def dormir(self):
		print(f"zzzz")


class Chien(Animal):

	def __init__(self, nom, age):
		super().__init__(nom, age)

	def __eq__(self, other: 'Chien'):
		return self.nom, self.age == other.nom, other.age

	def __gt__(self, other: 'Chien'):
		return self.age > other.age

	def __ge__(self, other: 'Chien'):
		return self.age >= other.age

	def aboyer(self):
		print("Ouaf ouaf")


if __name__ == '__main__':

	c1 = Chien('Toto', 4)
	print(c1.nom)
	print(c1.age)
	c1.manger('des croquettes')
	c1.aboyer()
