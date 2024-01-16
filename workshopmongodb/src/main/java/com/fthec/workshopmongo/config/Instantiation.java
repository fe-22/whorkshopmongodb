package com.fthec.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.fthec.workshopmongo.domain.Post;
import com.fthec.workshopmongo.domain.User;
import com.fthec.workshopmongo.dto.AuthorDTO;
import com.fthec.workshopmongo.dto.CommentDTO;
import com.fthec.workshopmongo.repository.PostRepository;
import com.fthec.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private PostRepository postRepository; 

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User priscila = new User(null, "Priscila Xavier Fernandes", "priscila@gmail.com");
		User gustavo = new User(null, "Gustavo Xavier Fernandes", "gustavo@gmail.com");
		User danilo = new User(null, "Danilo Xavier Fernandes", "danilo@gmail.com");
		
		userRepository.saveAll(Arrays.asList(priscila, gustavo, danilo));
		Post post1 = new Post(null, sdf.parse("14/01/2023"), "Partiu Viagem!", "Vou viajar para Portugal. Abraços!",new AuthorDTO(priscila));
		Post post2 = new Post(null, sdf.parse("14/01/2023"), "Bom Dia!", "Acordei feliz hoje!",new AuthorDTO(gustavo));
		
		CommentDTO c1 = new CommentDTO("Boa Viagem!", sdf.parse("16/01/2024"), new AuthorDTO(priscila));
		CommentDTO c2 = new CommentDTO("Olá irmão!", sdf.parse("15/01/2024"), new AuthorDTO(danilo));
		CommentDTO c3 = new CommentDTO("Deus e fiel!", sdf.parse("16/01/2024"), new AuthorDTO(gustavo));
		
		post1.getComments().addAll(Arrays.asList(c1));
		post1.getComments().addAll(Arrays.asList(c2));
		post1.getComments().addAll(Arrays.asList(c3));
	
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		priscila.getPosts().addAll(Arrays.asList(post1));
		gustavo.getPosts().addAll(Arrays.asList(post2));
		userRepository.save(priscila);
		userRepository.save(gustavo);
		
	}

}
