package com.pl.bakery.bakery;

import com.pl.bakery.bakery.dto.BakeryRequestDto;
import com.pl.bakery.bakery.dto.BakeryResponseDto;
import com.pl.bakery.bakery.exception.BakeryNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BakeryServiceImpl implements BakeryService {

    private final BakeryRepository bakeryRepository;

    private final BakeryMapper bakeryMapper;

    @Override
    public List<BakeryResponseDto> findAllBakeries() {
        List<BakeryEntity> bakeryEntities = bakeryRepository.findAll();

        return bakeryEntities.stream()
                .map(bakeryMapper::mapBakeryEntityToBakeryResponseDto)
                .toList();
    }

    @Override
    public BakeryResponseDto addBakery(BakeryRequestDto bakeryRequestDto) {

        BakeryEntity bakeryEntity =
                bakeryMapper.mapBakeryRequestDtoToBakeryEntity(bakeryRequestDto);

        bakeryRepository.save(bakeryEntity);
        log.info("Bakery {} is saved", bakeryEntity.getId());
        return bakeryMapper.mapBakeryEntityToBakeryResponseDto(bakeryEntity);
    }

    @Override
    public BakeryResponseDto findById(Long id) {
        BakeryEntity bakeryEntity =
                bakeryRepository.findById(id).orElseThrow(() -> new BakeryNotFoundException(id));
        return bakeryMapper.mapBakeryEntityToBakeryResponseDto(bakeryEntity);
    }

    @Override
    public BakeryResponseDto updateBakery(Long id, BakeryRequestDto bakeryRequestDto) {
        BakeryEntity bakeryEntity =
                bakeryRepository.findById(id).orElseThrow(() -> new BakeryNotFoundException(id));

        bakeryEntity.setName(bakeryRequestDto.getName());
        bakeryEntity.setAddress(bakeryRequestDto.getAddress());
        bakeryEntity.setLocation(bakeryRequestDto.getLocation());

        bakeryRepository.save(bakeryEntity);
        log.info("Bakery {} updated", bakeryEntity.getId());
        return bakeryMapper.mapBakeryEntityToBakeryResponseDto(bakeryEntity);
    }

    @Override
    public void deleteById(Long id) {

        bakeryRepository.deleteById(id);
    }
}
