package ouhk.comps380f.controller;

import ouhk.comps380f.model.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import java.util.Date;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import ouhk.comps380f.model.Attachment;
import ouhk.comps380f.model.Ticket;
import ouhk.comps380f.service.AttachmentService;
import ouhk.comps380f.view.DownloadingView;
import ouhk.comps380f.dao.CommentRepository;
import ouhk.comps380f.service.TicketService;

@Controller
@RequestMapping("unuser")
public class UnregistrationUserController  {
    
    @Resource
    private CommentRepository gbEntryRepo; 
     
    @Autowired
    private TicketService ticketService;
    
    @Autowired
    private AttachmentService attachmentService;
    
    @RequestMapping(value = "list2", method = RequestMethod.GET)
    public String list(ModelMap model) {
        model.addAttribute("ticketDatabase", ticketService.getTickets());
        return "list2";
    }
    
    @RequestMapping(value = "view2/{ticketId}", method = RequestMethod.GET)
    public String view(@PathVariable("ticketId") long ticketId,
            ModelMap model) {
        Ticket ticket = ticketService.getTicket(ticketId);
        if (ticket == null) {
            return "redirect:/item/list2";
        }
        model.addAttribute("ticket", ticket);
        return "view2";
    }
    
    @RequestMapping(
            value = "/{ticketId}/attachment/{attachment:.+}",
            method = RequestMethod.GET
    )
    public View download(@PathVariable("ticketId") long ticketId,
            @PathVariable("attachment") String name) {

        Attachment attachment = attachmentService.getAttachment(ticketId, name);
        if (attachment != null) {
            return new DownloadingView(attachment.getName(),
                    attachment.getMimeContentType(), attachment.getContents());
        }
        return new RedirectView("/unuser/list2", true);
    }
    
    /*@RequestMapping(value="addComment/{ticketId}", method=RequestMethod.GET)
    public ModelAndView addCommentForm(@PathVariable("ticketId") long ticketId, ModelMap model) {
        Ticket ticket = ticketService.getTicket(ticketId);
        model.addAttribute("ticket", ticket);
        return new ModelAndView("AddComment", "command", new GuestBookEntry());
    }
    
    @RequestMapping(value="addComment/{ticketId}", method=RequestMethod.POST)
    public View addCommentHandle(GuestBookEntry entry, @PathVariable("ticketId") long ticketId) {
        Ticket ticket = ticketService.getTicket(ticketId);
        entry.setDate(new Date());
        gbEntryRepo.addEntry(entry);
        return new RedirectView("/ticket/list", true);
    }
    
    @RequestMapping(value="edit", method=RequestMethod.GET)
    public String editCommentForm(@RequestParam("id") Integer entryId, ModelMap model) {
        GuestBookEntry entry = gbEntryRepo.getEntryById(entryId);
        model.addAttribute("entry", entry);
        return "EditComment";
    }
    

    
    @RequestMapping(value="viewComment/{ticketId}", method=RequestMethod.GET)
    public ModelAndView viewComment(@PathVariable("ticketId") long ticketId, ModelMap model) {
        Ticket ticket = ticketService.getTicket(ticketId);
        //Integer id = (int)ticketId;
        String name = Long.toString(ticketId);
        model.addAttribute("ticket", ticket);
        model.addAttribute("entries", gbEntryRepo.listEntries(name));
        return new ModelAndView("/GuestBook");
    }
    
    @RequestMapping(value="edit", method=RequestMethod.POST)
    public View editCommentHandle(GuestBookEntry entry) {
        entry.setDate(new Date());
        gbEntryRepo.updateEntry(entry);
        return new RedirectView("/guestbook/view", true);
    }

    @RequestMapping(value="delete", method=RequestMethod.GET)
    public View deleteEntry(@RequestParam("id") Integer entryId) {
        gbEntryRepo.removeEntryById(entryId);
        return new RedirectView("/ticket/list", true);
    }*/
    
}
