package com.curiel.catalogos.service;

import java.util.HashSet;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.curiel.catalogos.model.dto.ProductoDto;
import com.curiel.catalogos.model.entity.Producto;
import com.curiel.catalogos.repository.ProductoRepository;
import com.curiel.catalogos.util.GenericService;

@Service
public class ProductoService implements GenericService<ProductoDto, Producto, Long>  {
  
    @Autowired
    private ProductoRepository productoRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Override
    @Transactional(readOnly=true)
    public Set<ProductoDto> list() {
        Set<ProductoDto> productoDtolist=new HashSet<>();
        productoRepository.findAll().forEach(producto->productoDtolist.add(convertDto(producto)));
         return productoDtolist;
    }
    @Transactional(readOnly = true)
    public Page<Producto> paguinas(Pageable pageable){
        return productoRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void delete(Long id) {
         productoRepository.deleteById(id);
     }

    @Override
    @Transactional
    public ProductoDto save(ProductoDto dto) {
         Producto producto=productoRepository.save(convertToEntity(dto));
         return convertToDto(producto);
    }

    @Override
    @Transactional(readOnly=true)
    public ProductoDto getById(Long id) {
         Producto producto=productoRepository.getOne(id);
         return convertToDto(producto);
    }
    
    @Transactional(readOnly = true)
    public Set<ProductoDto> listProductosBySucursalIdAndStatus(Long sucursalId,int status){
        Set<ProductoDto> productoDtolist=new HashSet<>();
        productoRepository.findBySucursalIdAndStatus(sucursalId,status).forEach(productos->productoDtolist.add(convertDto(productos)));
        return  productoDtolist;
    }
    @Transactional(readOnly = true)
    public Set<ProductoDto> listProductosByCategorialIdAndStatus(Long categoriaId,int status){
        Set<ProductoDto> productoDtolist=new HashSet<>();
        productoRepository.findByCategoriaIdAndStatus(categoriaId,status).forEach(productos->productoDtolist.add(convertDto(productos)));
        return  productoDtolist;
    }
    @Transactional(readOnly = true)
    public Set<ProductoDto> listProductosByNombreLikeAndStatus(String nombre,int status){
        Set<ProductoDto> productoDtolist=new HashSet<>();
        productoRepository.findByNombreContainingAndStatus(nombre,status).forEach(productos->productoDtolist.add(convertDto(productos)));
        return  productoDtolist;
    }
    
    @Override
    public ProductoDto convertToDto(Producto entity) {
         return modelMapper.map(entity, ProductoDto.class);
    }

    @Override
    public Producto convertToEntity(ProductoDto dto) {
         return modelMapper.map(dto, Producto.class);
    }
    
    public ProductoDto convertDto(Producto entity) {
    	ProductoDto dto=new ProductoDto();
    	dto.setId(entity.getId());
    	dto.setNombre(entity.getNombre());
    	dto.setDescripcion(entity.getDescripcion());
    	dto.setPrecio(entity.getPrecio());
    	dto.setImagen(entity.getImagen()); 
        return dto;
    }
  
  
}
