# © Copyright IPC Systems, Inc. All Rights Reserved
import random
import unittest

class RandomTest(unittest.TestCase):

    """Test case utilisé pour tester les fonctions du module 'random'."""

    def test_choice(self):
        """Test le fonctionnement de la fonction 'random.choice'."""
        liste = list(range(10))
        elt = random.choice(liste)
        # Vérifie que 'elt' est dans 'liste'
        self.assertIn(elt, liste)

    # Autres méthodes de test
    def test_shuffle(self):
        """Test le fonctionnement de la fonction 'random.shuffle'."""
        liste = list(range(10))
        random.shuffle(liste)
        liste.sort()
        self.assertEqual(liste, list(range(10)))

    # Autres méthodes de test
    def test_sample(self):
        """Test le fonctionnement de la fonction 'random.sample'."""
        liste = list(range(10))
        extrait = random.sample(liste, 5)
        for element in extrait:
            self.assertIn(element, liste)

        with self.assertRaises(ValueError):
            random.sample(liste, 20)

unittest.main()