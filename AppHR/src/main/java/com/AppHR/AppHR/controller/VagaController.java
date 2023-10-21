package com.AppHR.AppHR.controller;

import com.AppHR.AppHR.model.Candidate;
import com.AppHR.AppHR.model.Vaga;
import com.AppHR.AppHR.repository.CandidateRepository;
import com.AppHR.AppHR.repository.VagaRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VagaController {

    private VagaRepository vagaRepository;

    private CandidateRepository candidateRepository;




@RequestMapping(value = "/cadastrarVaga",method = RequestMethod.GET)
    public String form(){
        return "vaga/form-vaga";
    }


    @RequestMapping(value = "/cadastrarVaga",method = RequestMethod.POST)
    public String form(@Valid Vaga vaga , BindingResult result, RedirectAttributes attributes){
       if (result.hasErrors()){
           attributes.addFlashAttribute("mensagem","Invalid input, verify the filds again...");
           return "redirect:/cadastrarVaga";
       }
       vagaRepository.save(vaga);
       attributes.addFlashAttribute("mensagem","Your registration was successful applied");

       return "redirect:/cadastrarVaga";
    }

    //LiSTA VAGAS
    @RequestMapping("/vagas")
    public ModelAndView listaVagas(){
        ModelAndView modelAndView = new ModelAndView("vaga/lista-vaga");
        Iterable<Vaga>vagas = vagaRepository.findAll();
        modelAndView.addObject("vagas", vagas);
        return modelAndView;
    }

    //
    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    public ModelAndView detalhesVaga(@PathVariable("code") long code){
        Vaga vaga = vagaRepository.findByCode(code);
        ModelAndView modelAndView = new ModelAndView("vaga/detalhes-vaga");
        modelAndView.addObject("vaga", vaga);

        Iterable<Candidate>candidates = candidateRepository.findByVaga(vaga);
        modelAndView.addObject("candidates", candidates);
        return modelAndView;
    }


    // Delete offer

    @RequestMapping("/deletarVaga")
    public String deletarVaga(long code){
       Vaga vaga = vagaRepository.findByCode(code);
       vagaRepository.delete(vaga);
       return "redirect:/vagas";
    }


    public String detalhesVagaPost(@PathVariable("code") long code, @Valid Candidate candidate, BindingResult result, RedirectAttributes attributes){
       if(result.hasErrors()){
           attributes.addFlashAttribute("mensagem","Verifique os campos");
           return "redirect:/{code}";
       }
      if(candidateRepository.findByRg(candidate.getRg()) != null){
          attributes.addFlashAttribute("mensagem erro","B.I duplicado");

          return "redirect:/{code}";
      }

      Vaga vaga = vagaRepository.findByCode(code);
      candidate.setVaga(vaga);
      candidateRepository.save(candidate);
      attributes.addFlashAttribute("mensagem","Candidato adicionado com sucesso");
    return "redirect:/{code}";
    }

    //Delete candidate

    @RequestMapping("/deletarCandidato")
    public String deletarCadidato(String rg){
       Candidate candidate = candidateRepository.findByRg(rg);
       Vaga vaga = candidate.getVaga();
       String codigo = "" + vaga.getCode();

       candidateRepository.delete(candidate);

       return "redirect:/" + codigo;
    }

    //Update offers
    //forms

    @RequestMapping(value ="/editar-vaga", method = RequestMethod.GET)
    public ModelAndView editVaga(long code){
     Vaga vaga = vagaRepository.findByCode(code);
     ModelAndView modelAndView = new ModelAndView("vaga/update-vaga");
     modelAndView.addObject("vaga",vaga);
     return modelAndView;
    }

    //Update offer
    @RequestMapping(value ="/editar-vaga", method = RequestMethod.POST)
    public String updateVaga(@Valid Vaga vaga,BindingResult result,RedirectAttributes attributes){
       vagaRepository.save(vaga);
       attributes.addFlashAttribute("success","Vagag alterada com successo");
       long codelong = vaga.getCode();
       String codigo = "" + codelong;

       return "redirect:/"+codigo;
    }

}
