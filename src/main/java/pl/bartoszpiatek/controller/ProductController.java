package pl.bartoszpiatek.controller;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import pl.bartoszpiatek.exceptions.InvalidFileException;
import pl.bartoszpiatek.model.dto.FileInfo;
import pl.bartoszpiatek.model.dto.ProductCategoryEnum;
import pl.bartoszpiatek.model.entity.Product;
import pl.bartoszpiatek.service.FileService;
import pl.bartoszpiatek.service.ProductService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	

	
	@Autowired
	private FileService fileService;

	@Value("${photo.upload.directory}")
	private String photoUploadDirectory;

	@RequestMapping(value = "/addproduct", method = RequestMethod.GET)
	ModelAndView addProductGET(ModelAndView modelAndView, @ModelAttribute("product") Product product, 
			ProductCategoryEnum categories) {

		modelAndView.getModel().put("categories", ProductCategoryEnum.values());
		modelAndView.setViewName("app.addProduct");
		return modelAndView;
	}

	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	ModelAndView addProductPOST(ModelAndView modelAndView, @Valid Product product, BindingResult result,
			@RequestParam("file") MultipartFile file) {

		modelAndView.setViewName("app.addProduct");
	
		
		Path oldPotoPath = product.getPhoto(photoUploadDirectory);
		
		try {
			FileInfo photoInfo = fileService.saveFile(file, photoUploadDirectory, "photos", "profile");
			product.setPhotoDetails(photoInfo); 
			
			if(oldPotoPath != null){
				Files.delete(oldPotoPath);
			}
			
		} catch (InvalidFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!result.hasErrors()) {
			
			productService.save(product);
			modelAndView.setViewName("redirect:/");
		}

		return modelAndView;
	}


	@RequestMapping(value = "/deleteproduct", method = RequestMethod.GET)
	ModelAndView deleteProductGET(ModelAndView modelAndView, @RequestParam(name = "id") Long id) {

		productService.delete(id);

		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}

	@RequestMapping(value = "/editproduct", method = RequestMethod.GET)
	ModelAndView editProductGET(ModelAndView modelAndView, @RequestParam(name = "id") Long id) {

		Product product = productService.get(id);
		

		modelAndView.getModel().put("product", product);
		modelAndView.setViewName("app.editProduct");
		return modelAndView;
	}

	@RequestMapping(value = "/editproduct", method = RequestMethod.POST)
	ModelAndView editProductPOST(ModelAndView modelAndView, @Valid Product product, BindingResult result,
			@RequestParam("file") MultipartFile file) {
	
		modelAndView.setViewName("app.addProduct");

		Path oldPotoPath = product.getPhoto(photoUploadDirectory);
		
		try {
			FileInfo photoInfo = fileService.saveFile(file, photoUploadDirectory, "photos", "profile");
			product.setPhotoDetails(photoInfo); 
			
			if(oldPotoPath != null){
				Files.delete(oldPotoPath);
			}
			
		} catch (InvalidFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		modelAndView.setViewName("app.editProduct");
		if (!result.hasErrors()) {
			
			productService.save(product);
			modelAndView.setViewName("redirect:/");
		}

		return modelAndView;
	}
	
	
//	SEND RAW DATA OF THE PHOTO
	@RequestMapping(value = "/productphoto", method = RequestMethod.GET)
	@ResponseBody
	ResponseEntity<InputStreamResource> servePhotoGET(@RequestParam(name = "id") Long id) throws IOException{

		Product product = productService.get(id);

		Path photoPath = Paths.get(photoUploadDirectory, "default", "test.jpg");
		if (product != null && product.getPhoto(photoUploadDirectory) != null) {
			photoPath = product.getPhoto(photoUploadDirectory);
		}
		
		return ResponseEntity
				.ok()
				.contentLength(Files.size(photoPath))
				.contentType(MediaType.parseMediaType(URLConnection.guessContentTypeFromName(photoPath.toString())))
				.body(new InputStreamResource(Files.newInputStream(photoPath, StandardOpenOption.READ)));
	}
	
	
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	ModelAndView getProductGET(ModelAndView modelAndView, @PathVariable(name = "id") Long id) {

		Product product = productService.get(id);

		modelAndView.getModel().put("product", product);
		modelAndView.setViewName("app.product");
		return modelAndView;
	}

}
