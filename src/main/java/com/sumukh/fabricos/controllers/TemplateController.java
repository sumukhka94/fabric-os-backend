package com.sumukh.fabricos.controllers;

import com.sumukh.fabricos.Dtos.TemplateListDto;
import com.sumukh.fabricos.Dtos.TemplateSubjectDto;
import com.sumukh.fabricos.Entities.Template;
import com.sumukh.fabricos.Repositories.TemplateRepository;
import com.sumukh.fabricos.enums.Channel;
import com.sumukh.fabricos.utils.TemplateUtils;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/templates")
@CrossOrigin(origins = "http://localhost:5173")
public class TemplateController {

    private final TemplateRepository templateRepository;

    public TemplateController(TemplateRepository templateRepository) {
        this.templateRepository = templateRepository;
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<TemplateListDto>> getAllTemplateList(){
        List<Template> allTemplates = templateRepository.findAll();

        List<TemplateListDto> templateListDto = allTemplates.stream()
                .map(template -> new TemplateListDto(String.valueOf(template.getId()), template.getTemplateName() , template.getChannel() ))
                .toList();

        return ResponseEntity.ok(templateListDto);
    }

    @GetMapping("/subject/{id}")
    public ResponseEntity<TemplateSubjectDto> getTemplateSubject(@PathVariable String id){
        Template t = templateRepository.findById(Long.parseLong(id)).orElse(null);
        if(t == null){
            return ResponseEntity.notFound().build();
        }

        TemplateSubjectDto res = new TemplateSubjectDto();
        res.setId(String.valueOf(t.getId()));
        res.setSubject(t.getSubject());

        return ResponseEntity.ok(res);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TemplateListDto> deleteTemplate(@PathVariable String id){
        Template t = templateRepository.findById(Long.parseLong(id)).orElse(null);
        if(t == null){
            return ResponseEntity.notFound().build();
        }
        templateRepository.deleteById(Long.parseLong(id));

        TemplateListDto res = new TemplateListDto();

        res.setId(String.valueOf(t.getId()));
        res.setTemplateName(t.getTemplateName());
        res.setChannel(t.getChannel());

        return ResponseEntity.ok(res);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<TemplateSubjectDto> editTemplateSubject(@PathVariable String id, @RequestBody Map<String,String> request){
        String subject = request.get("subject");
        Template t = templateRepository.findById(Long.parseLong(id)).orElse(null);
        if(t == null){
            return ResponseEntity.notFound().build();
        }

        t.setSubject(subject);
        Template edited = templateRepository.save(t);
        TemplateSubjectDto res = new TemplateSubjectDto();
        res.setId(String.valueOf(edited.getId()));
        res.setSubject(edited.getSubject());

        return ResponseEntity.ok(res);
    }

    @PostMapping("/add")
    public ResponseEntity<TemplateListDto> addTemplate(@RequestBody Map<String,String> request){
        Template t = new Template();
        t.setSubject(request.get("subject"));
        t.setTemplateName(request.get("templateName"));
        String channelStr = request.get("selectedChannel");
        Channel channel = channelStr.equals("EMAIL") ? Channel.EMAIL : Channel.SMS;
        t.setChannel(channel);

        Template saved = templateRepository.save(t);

        TemplateListDto response = new TemplateListDto(String.valueOf(saved.getId()),saved.getTemplateName(),saved.getChannel());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/get-preview")
    public ResponseEntity<Map<String,String>> getPreview(@RequestBody Map<String,String> request){
        String subject = request.get("subject");
        String replaced = TemplateUtils.replaceNamePlaceholder(subject, "Sumukh");

        return ResponseEntity.ok(Map.of("subject" , replaced));
    }
}
