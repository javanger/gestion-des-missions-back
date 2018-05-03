package dev.model;

import javax.persistence.Embeddable;

@Embeddable
public enum Transport {
	AVION, COVOITURAGE, TRAIN, VOITURE_DE_SERVICE
}
