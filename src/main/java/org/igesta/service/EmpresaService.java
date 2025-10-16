package org.igesta.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.igesta.dto.EmpresaRequestDTO;
import org.igesta.dto.EmpresaResponseDTO;
import org.igesta.model.Empresa;
import org.igesta.repository.EmpresaRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;
    private final ObjectMapper objectMapper;


    public EmpresaService(EmpresaRepository empresaRepository, ObjectMapper objectMapper) {
        this.empresaRepository = empresaRepository;
        this.objectMapper = objectMapper;
    }

    public List<Empresa> buscarEmpresas() {
        return empresaRepository.findAll();
    }

    public EmpresaResponseDTO buscarEmpresaPorId(Long id) {
        return objectMapper.convertValue(empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada")), EmpresaResponseDTO.class);
    }

    @Transactional
    public EmpresaResponseDTO adicionarEmpresa(EmpresaRequestDTO dto){
        Empresa empresa = objectMapper.convertValue(dto, Empresa.class);
        return objectMapper.convertValue(empresaRepository.save(empresa), EmpresaResponseDTO.class);
    }

    @Transactional
    public EmpresaResponseDTO removerEmpresa(Long id){
        EmpresaResponseDTO existente = buscarEmpresaPorId(id);
        empresaRepository.deleteById(id);
        return existente;
    }

    @Transactional
    public EmpresaResponseDTO atualizarEmpresa(Long id, EmpresaRequestDTO dto) {
        if (!empresaRepository.existsById(id)) {
            throw new EntityNotFoundException("Empresa não encontrada para atualizar");
        }
        Empresa atualizado =objectMapper.convertValue(dto, Empresa.class);
        atualizado.setId(id);
        return objectMapper.convertValue(empresaRepository.save(atualizado), EmpresaResponseDTO.class);
    }

    @Transactional
    public EmpresaResponseDTO atualizarEmpresaParcial(Long id, EmpresaRequestDTO dto) {
        if (!empresaRepository.existsById(id)) {
            throw new EntityNotFoundException("Empresa não encontrada para atualização parcial");
        }
        Empresa empresa = empresaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada"));

        Empresa atualizacoes =objectMapper.convertValue(dto, Empresa.class);

        if (atualizacoes.getNome() != null) {
            String nome = atualizacoes.getNome();
            empresa.setNome(nome);
        }

        if (atualizacoes.getCnpj() != null) {
            String cnpj =  atualizacoes.getCnpj();
            empresa.setCnpj(cnpj);
        }

        return objectMapper.convertValue(empresaRepository.save(empresa), EmpresaResponseDTO.class);
    }

    public List<EmpresaResponseDTO> buscarEmpresaPorNome(String nome) {
        List<Empresa> empresas = empresaRepository.findByNome(nome);
        if (empresas == null){
            throw new EntityNotFoundException("Empresa não encontrada");
        }
        List<EmpresaResponseDTO> listaResponseDTO = new ArrayList<>();
        for (Empresa empresa : empresas){
            listaResponseDTO.add(objectMapper.convertValue(empresa, EmpresaResponseDTO.class));
        }
        return listaResponseDTO;
    }

    public List<EmpresaResponseDTO> buscarEmpresaPorParteDoNome(String nome) {
        List<Empresa> empresas = empresaRepository.findByParteNome(nome);
        if (empresas.isEmpty()){
            throw new EntityNotFoundException("Nenhuma empresa encontrada");
        }
        List<EmpresaResponseDTO> listaResponseDTO =new ArrayList<>();
        for (Empresa empresa : empresas){
            listaResponseDTO.add(objectMapper.convertValue(empresa, EmpresaResponseDTO.class));
        }
        return listaResponseDTO;
    }

    public List<EmpresaResponseDTO> buscarEmpresaPorCnpj(String cnpj) {
        List<Empresa> empresas = empresaRepository.findByCnpj(cnpj);
        if (empresas.isEmpty()){
            throw new EntityNotFoundException("Nenhuma empresa encontrada");
        }
        List<EmpresaResponseDTO> listaResponseDTO =new ArrayList<>();
        for (Empresa empresa : empresas){
            listaResponseDTO.add(objectMapper.convertValue(empresa, EmpresaResponseDTO.class));
        }
        return listaResponseDTO;
    }
}
