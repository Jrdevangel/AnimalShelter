package com.example.animalshelter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.animalshelter.model.Donation;
import com.example.animalshelter.repositories.IDonationRepository;
import com.example.animalshelter.services.DonationService;

@ExtendWith(MockitoExtension.class)
public class DonationServiceTest {

	@Mock
	private IDonationRepository donationRepository;

	@InjectMocks
	private DonationService donationService;

	@BeforeEach
	public void setup() {
	}

	@Test
	public void testCreateDonation() {
		Donation donation = new Donation();
		donation.setId(1L);
		donation.setAmount(100);
		donation.setDonorName("John Doe");

		when(donationRepository.save(any(Donation.class))).thenReturn(donation);

		Donation result = donationService.createDonation(donation);

		assertEquals(donation, result);
		verify(donationRepository, times(1)).save(donation);
	}

	@Test
	public void testGetAllDonations() {
		Donation donation = new Donation();
		donation.setId(1L);
		donation.setAmount(100);
		donation.setDonorName("John Doe");

		when(donationRepository.findAll()).thenReturn(Collections.singletonList(donation));

		List<Donation> result = donationService.getAllDonations();

		assertEquals(1, result.size());
		assertEquals(donation, result.get(0));
		verify(donationRepository, times(1)).findAll();
	}

	@Test
	public void testUpdateDonation() {
		Donation existingDonation = new Donation();
		existingDonation.setId(1L);
		existingDonation.setAmount(100);
		existingDonation.setDonorName("John Doe");

		Donation updatedDonation = new Donation();
		updatedDonation.setId(1L);
		updatedDonation.setAmount(150);
		updatedDonation.setDonorName("Jane Doe");

		when(donationRepository.existsById(1L)).thenReturn(true);
		when(donationRepository.save(any(Donation.class))).thenReturn(updatedDonation);

		donationService.updateDonation(updatedDonation, 1L);

		verify(donationRepository, times(1)).save(updatedDonation);
	}

	@Test
	public void testUpdateDonation_NotFound() {
		Donation donation = new Donation();
		donation.setId(1L);

		when(donationRepository.existsById(1L)).thenReturn(false);

		assertThrows(NoSuchElementException.class, () -> {
			donationService.updateDonation(donation, 1L);
		});

		verify(donationRepository, times(0)).save(donation);
	}

	@Test
	public void testDeleteDonationById() {
		when(donationRepository.existsById(1L)).thenReturn(true);

		String result = donationService.deleteDonationById(1L);

		assertEquals("Donation Deleted 1", result);
		verify(donationRepository, times(1)).deleteById(1L);
	}

	@Test
	public void testDeleteDonationById_NotFound() {
		when(donationRepository.existsById(1L)).thenReturn(false);

		String result = donationService.deleteDonationById(1L);

		assertEquals("Donation not found with id: 1", result);
		verify(donationRepository, times(0)).deleteById(1L);
	}
}
