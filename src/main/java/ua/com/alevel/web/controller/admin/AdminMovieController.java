package ua.com.alevel.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.com.alevel.facade.movie.MovieFacade;
import ua.com.alevel.web.controller.AbstractController;
import ua.com.alevel.web.dto.request.MovieRequestDto;
import ua.com.alevel.web.dto.response.MovieResponseDto;
import ua.com.alevel.web.dto.response.PageData;


@Controller
@RequestMapping("/admin/books")
public class AdminMovieController extends AbstractController {

    private final HeaderName[] columnNames = new HeaderName[] {
            new HeaderName("#", null, null),
            new HeaderName("image", null, null),
            new HeaderName("book name", "bookName", "book_name"),
            new HeaderName("page size", "pageSize", "page_size"),
            new HeaderName("publication", "publicationDate", "publication_date"),
            new HeaderName("created", "created", "created"),
            new HeaderName("price", "price", "price"),
            new HeaderName("quantity", "quantity", "quantity"),
            new HeaderName("details", null, null),
            new HeaderName("delete", null, null)
    };

    private final MovieFacade movieFacade;

    public AdminMovieController(MovieFacade movieFacade) {
        this.movieFacade = movieFacade;
    }

    @GetMapping
    public String findAll(Model model, WebRequest request) {
        PageData<MovieResponseDto> response = movieFacade.findAll(request);
        initDataTable(response, columnNames, model);
        model.addAttribute("createUrl", "/admin/movies/all");
        model.addAttribute("createNew", "/admin/movies/new");
        model.addAttribute("cardHeader", "All Movies");
        return "pages/admin/movie/movie_all";
    }

    @PostMapping("/all")
    public ModelAndView findAllRedirect(WebRequest request, ModelMap model) {
        return findAllRedirect(request, model, "movies");
    }

    @GetMapping("/new")
    public String redirectToNewMoviePage(Model model) {
        model.addAttribute("movie", new MovieRequestDto());
        return "pages/admin/movie/movie_new";
    }

    @PostMapping("/create")
    public String createNewDepartment(RedirectAttributes attributes, @ModelAttribute("movie") MovieRequestDto dto, @RequestParam("file") MultipartFile file) {
        movieFacade.create(dto);
        return "redirect:/admin/movies";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        movieFacade.delete(id);
        return "redirect:/admin/books";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable Long id, Model model) {
        MovieResponseDto dto = movieFacade.findById(id);
        model.addAttribute("movie", dto);
        return "pages/admin/movie/movie_details";
    }
}