package com.librarymanagement.service;

import com.librarymanagement.domain.model.Publishers;
import com.librarymanagement.domain.request.PublishersRequestDto;
import com.librarymanagement.domain.response.PublishersResponseDto;
import com.librarymanagement.exception.DataNotFoundException;
import com.librarymanagement.repository.PublisherRepository;
import com.librarymanagement.util.ErrorMessages;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
public class PublishersService {

    @Autowired
    private final PublisherRepository publisherRepository;
    @Autowired
    private final ModelMapper modelMapper;

    public PublishersRequestDto savePublisher(PublishersRequestDto publisherDto) {
        Publishers publisher = modelMapper.map(publisherDto, Publishers.class);
        Publishers savedPublisher = publisherRepository.save(publisher);
        return modelMapper.map(savedPublisher, PublishersRequestDto.class);
    }

    public List<PublishersResponseDto> getPublishers() {
        List<Publishers> publishers = publisherRepository.findAll();
        return publishers.stream()
                .map(publisher -> modelMapper.map(publisher, PublishersResponseDto.class))
                .collect(Collectors.toList());
    }

    public PublishersResponseDto getPublisherById(UUID publisherId) {
        Publishers publisher = publisherRepository.findById(publisherId)
                .orElseThrow(() -> new DataNotFoundException(ErrorMessages.PUBLISHER_NOT_FOUND.getValue()));
        return modelMapper.map(publisher, PublishersResponseDto.class);
    }

    public PublishersRequestDto updatePublisher(UUID publisherId, PublishersRequestDto updatedPublisherDto) {
        Publishers existingPublisher = publisherRepository.findById(publisherId)
                .orElseThrow(() -> new DataNotFoundException(ErrorMessages.PUBLISHER_NOT_FOUND.getValue()));

        existingPublisher.setName(updatedPublisherDto.getName());
        existingPublisher.setCountry(updatedPublisherDto.getCountry());

        Publishers savedPublisher = publisherRepository.save(existingPublisher);
        return modelMapper.map(savedPublisher, PublishersRequestDto.class);
    }

    public void deletePublisher(UUID publisherId) {
        Publishers publisher = publisherRepository.findById(publisherId)
                .orElseThrow(() -> new DataNotFoundException(ErrorMessages.PUBLISHER_NOT_FOUND.getValue()));

        publisherRepository.deleteById(publisherId);
    }
}

