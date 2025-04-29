import random


class RandomRange:

	def __init__(self, mini: int, maxi: int, limite: int):
		self.mini = mini
		self.maxi = maxi
		self.limite = limite
		self._elements = []

	def __str__(self):
		# return str(self._elements).replace('[', '<').replace(']', '>')
		return "<" + ', '.join([str(e) for e in self._elements]) + ">"

	def __iter__(self):
		return self

	def __next__(self):
		if len(self._elements) == self.limite:
			raise StopIteration
		next_element = random.randint(self.mini, self.maxi - 1)
		self._elements.append(next_element)
		return next_element

	def __add__(self, other: 'RandomRange'):
		new_rr = RandomRange(min([self.mini, other.mini]), max([self.maxi, other.maxi]), self.limite + other.limite)
		new_rr._elements = self._elements + other._elements
		return new_rr


if __name__ == '__main__':

	rr1 = RandomRange(10, 50, 8)
	rr2 = RandomRange(40, 80, 12)

	print(next(rr1))
	print(next(rr1))
	print(next(rr1))
	print(next(rr2))
	print(next(rr2))
	print(next(rr2))
	rr3 = rr1 + rr2
	print(rr3)
	print('...')
	print(rr3.mini)
	print(rr3.maxi)
	print(rr3.limite)