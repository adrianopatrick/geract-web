package com.implementsystem.geract.services.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.ejb.Stateless;

import com.implementsystem.geract.entity.Alunos;

@Stateless(mappedName="ejb/ImportadorService")
public class ImportadorServiceImpl {
	
	public List<Alunos> importa(File file) throws IOException{
		
		List<Alunos> alunos = new ArrayList<Alunos>();
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		String linha;
		while((linha = br.readLine()) != null){
			int indiceToken = 0;
			StringTokenizer token = new StringTokenizer(linha, " ");
			while(token.hasMoreTokens()){
				Alunos aluno = new Alunos();
				String valor = token.nextToken();
				switch (indiceToken) {
					case 0:{ aluno.setNome(valor); break; }
					case 1:{ aluno.setMatricula(valor); break; }
				}
				alunos.add(aluno);
				indiceToken++;
			}
		}
		
		return alunos;
	}

}
