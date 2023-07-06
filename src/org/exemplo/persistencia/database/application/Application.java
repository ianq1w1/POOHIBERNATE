package org.exemplo.persistencia.database.application;

//import org.exemplo.persistencia.database.dao.ExameDAO;
import org.exemplo.persistencia.database.dao.IEntityDAO;
import org.exemplo.persistencia.database.dao.TransacaoDAO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import org.exemplo.persistencia.database.dao.ClienteDAO;
import org.exemplo.persistencia.database.dao.ContaDAO;
import org.exemplo.persistencia.database.db.ConexaoBancoHibernate;
import org.exemplo.persistencia.database.enumeration.TipoConta;
import org.exemplo.persistencia.database.enumeration.TipoTransacao;
//import org.exemplo.persistencia.database.model.Exame;
import org.exemplo.persistencia.database.model.Cliente;
import org.exemplo.persistencia.database.model.Conta;
import org.exemplo.persistencia.database.model.Transacao;



public class Application {

	public static void main(String[] args) {
		
	
		try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				System.out.println("Bem-vindo ao banco! O que você gostaria de fazer?");
				System.out.println("1. Cadastrar novo cliente");
				System.out.println("2. Selecionar cliente existente");
				System.out.println("3. Sair");
				System.out.println("4. Listar todos os clientes cadastrados");
				System.out.println("5. Remover cliente");
				

				int opcao = scanner.nextInt();
				scanner.nextLine();

				switch (opcao) {
				case 1:
						System.out.println("digite seu CPF:");
						String cpf = scanner.nextLine();
						System.out.println("digite seu nome:");
						String nome = scanner.nextLine();
						

						try {
						
						Cliente cli = new Cliente(cpf, nome);
						cli.setCpf(cpf);
						cli.setNome(nome);
						
						IEntityDAO<Cliente> ClienteDao = new ClienteDAO(new ConexaoBancoHibernate());
						
						ClienteDao.save(cli);
						System.out.println("Cliente cadastrado!!! :D");
						} catch (Exception e) {
							System.out.println("Erro no cadastro do cliente:" + e.getMessage());
							e.printStackTrace();
						}
						break;
						
				case 2: 
					try {
						
						IEntityDAO<Cliente> ClienteDao = new ClienteDAO(new ConexaoBancoHibernate());
						
						System.out.println("Digite o CPF do cliente:");
						cpf = scanner.nextLine();
						
						Cliente cliente = ClienteDao.findByCpf2(cpf);
						
						if (cliente != null) {
							System.out.println("Cliente encontrado");

							System.out.println("resultado da busca: " + cliente.getNome());
							System.out.println("\nO que você gostaria de fazer?\n");
							System.out.println("1. Criar nova conta");
							System.out.println("2. Ver informações das contas");
							System.out.println("3. Realizar Deposito");
							System.out.println("4. Realizar Saque");
							System.out.println("5. Realizar Transferencia");
							System.out.println("6. Imprimir extrato");
							System.out.println("7. Remover conta");
							System.out.println("8. Balanco entre contas");
							
							opcao = scanner.nextInt();
							scanner.nextLine();
							
							switch(opcao) {
							case 1:
								System.out.println("Que tipo de conta deseja criar?");
								System.out.println("1. Conta Poupanca");
								System.out.println("2. Conta Corrente");
								
								opcao = scanner.nextInt();
								scanner.nextLine();
								
								switch (opcao) {
								case 1:
								
									try {
										IEntityDAO<Conta> Condao = new ContaDAO(new ConexaoBancoHibernate());
										Conta conta = new Conta();
										conta.setDataAbertura(LocalDateTime.now());
										conta.setSaldo(BigDecimal.ZERO);
										conta.setStatus(true);
										conta.setTipoConta(TipoConta.POUPANCA);
										
										conta.setCliente(cliente);
										
										
										Condao.save(conta);
										
										System.out.println("Conta Poupança criada ");
										
									} catch (Exception e) {
										System.out.println("erro ao criar a conta:" + e.getMessage());
										e.printStackTrace();
									}
								
								break;
								case 2:
							   
			                    try {
										
			                    	IEntityDAO<Conta> Contadao = new ContaDAO(new ConexaoBancoHibernate());
										Conta conta = new Conta();
										conta.setSaldo(BigDecimal.ZERO);
										conta.setDataAbertura(LocalDateTime.now());
										conta.setStatus(true);
										conta.setTipoConta(TipoConta.CORRENTE);
						
										
										conta.setCliente(cliente);
										Contadao.save(conta);
										
										System.out.println("Conta Corrente criada");

									} catch (Exception e) {
										System.out.println(" erro ao criar a conta:" + e.getMessage());
										e.printStackTrace();
									}
								break;
								default:
									System.out.println("Opção inválida");
									break;
								}
								
							case 2:
								
								try {
									
									
									if (cliente.getContas().isEmpty()) {
										System.out.println("Cliente não tem contas");
									} else {
										
										
										System.out.println("Contas do cliente:");
										for (Conta conta : cliente.getContas()) {
											System.out.println("Numero da Conta: " + conta.getNumeroconta());
											System.out.println("Saldo: " + conta.getSaldo());
											System.out.println("Tipo da Conta: " + conta.getTipoConta());
											System.out.println("Status: " + conta.isStatus());
											System.out.println("Data de Abertura: " + conta.getDataAbertura());
											
										}
									
									}
								} catch (Exception e) {
									System.out.println("erro ao listar as contas do cliente: " + e.getMessage());
									e.printStackTrace();
								}
								
								break;
							case 3:
								 
								try {
									
										List<Conta> contas = cliente.getContas();
										
										if (contas.isEmpty()) {
											System.out.println("O cliente não possui contas.");
										} else {
											System.out.println("Lista de contas do cliente:");
											for (Conta conta : contas) {
												System.out.println("Numero da Conta: " + conta.getNumeroconta());
												System.out.println("Saldo: " + conta.getSaldo());
												System.out.println("Tipo da Conta: " + conta.getTipoConta());
												System.out.println("Status: " + conta.isStatus());
												System.out.println("Data de Abertura: " + conta.getDataAbertura());
											
											}
											
											System.out.println("Degite o número da conta que deseja realisar o depósito:");
											int numeroConta = scanner.nextInt();
											scanner.nextLine();
											
											Conta contaSelecionada = null;
											for (Conta conta : contas) {
												if (conta.getNumeroconta() == numeroConta) {
													contaSelecionada = conta;
													break;
												}
											}
											if (contaSelecionada != null) {
												System.out.println("Digite o valor do depósito:");
												double quantia = scanner.nextDouble();
												scanner.nextLine();
												
												contaSelecionada.depositar(new BigDecimal(quantia));
												
												IEntityDAO<Transacao> TransacaoDAO = new TransacaoDAO(new ConexaoBancoHibernate()); 
												Transacao transacao = new Transacao(BigDecimal.valueOf(quantia), TipoTransacao.CREDITO, LocalDateTime.now());
												transacao.setConta(contaSelecionada);
												
												
												IEntityDAO<Conta> Condao = new ContaDAO(new ConexaoBancoHibernate());
												Condao.update(contaSelecionada);
												TransacaoDAO.save(transacao);
												System.out.println("Depósito realizado");
											} else {
												System.out.println("Conta não encontrada;");
											}
										}
								} catch (Exception e) {
									System.out.println("erro ao realizar depósito: " + e.getMessage());
									e.printStackTrace();
								}
								
								break;
							case 4:
								
								try {
									
										List<Conta> contas = cliente.getContas();
										
										if (contas.isEmpty()) {
											System.out.println("O cliente selecionado não possui contas");
										} else {
											System.out.println("lista de contas do cliente: ");
											for (Conta conta : contas) {
												System.out.println("Numero da Conta: " + conta.getNumeroconta());
												System.out.println("Saldo: " + conta.getSaldo());
												System.out.println("Tipo da Conta: " + conta.getTipoConta());
												System.out.println("Status: " + conta.isStatus());
												System.out.println("Data de Abertura: " + conta.getDataAbertura());
											}
											
											System.out.println("Digite o numero da conta que deseja sacar:");
											int numeroConta = scanner.nextInt();
											scanner.nextLine();
											
											Conta contaSelecionada = null;
											for (Conta conta : contas) {
												if (conta.getNumeroconta() == numeroConta) {
													contaSelecionada = conta;
													break;
												}
											}
											if (contaSelecionada != null) {
												System.out.println("Digite o valor do saque:");
												double quantia = scanner.nextDouble();
												scanner.nextLine();
												contaSelecionada.sacar(new BigDecimal(quantia));
												
												IEntityDAO<Transacao> TransacaoDAO = new TransacaoDAO(new ConexaoBancoHibernate()); 
												Transacao transacao = new Transacao(BigDecimal.valueOf(quantia), TipoTransacao.CREDITO, LocalDateTime.now());
												transacao.setConta(contaSelecionada);
												
												IEntityDAO<Conta> Condao = new ContaDAO(new ConexaoBancoHibernate());
												Condao.update(contaSelecionada);
												TransacaoDAO.save(transacao);

												


												System.out.println("Saque realizado");
											} else {
												System.out.println("Conta não encontrada.");
											}
										}
				
								} catch (Exception e) {
									System.out.println("erro ao realizar o saque: " + e.getMessage());
									e.printStackTrace();
								}
								
								break;
							case 5:
								try {
									
										List<Conta> contas = cliente.getContas();
										
										if (contas.isEmpty()) {
											System.out.println("O cliente não possui contas");
										} else {
											System.out.println("lista de contas do cliente: ");
											for (Conta conta : contas) {
												System.out.println("Numero da Conta: " + conta.getNumeroconta());
												System.out.println("Saldo: " + conta.getSaldo());
												System.out.println("Tipo da Conta: " + conta.getTipoConta());
												System.out.println("Status: " + conta.isStatus());
												System.out.println("Data de Abertura: " + conta.getDataAbertura());
												
											}
											
											IEntityDAO<Conta> Condao = new ContaDAO(new ConexaoBancoHibernate());
											System.out.println("Digite o número da conta de origem: ");
									        int numeroContaOrigem = scanner.nextInt();
									        Conta contaOrigem = Condao.findById(numeroContaOrigem);

									        System.out.println("Digite o número da conta de destino: ");
									        int numeroContaDestino = scanner.nextInt();
									        Conta contaDestino = Condao.findById(numeroContaDestino);
									        
									        if (contaOrigem != null || contaDestino != null) {
									        	System.out.println("Digite o valor da transferencia:");
									        	double quantia = scanner.nextDouble();
									        	scanner.nextLine();
									        	
									        	contaOrigem.transferir(contaDestino, new BigDecimal(quantia));
									        	
									        	IEntityDAO<Transacao> TransacaoDAO = new TransacaoDAO(new ConexaoBancoHibernate()); 
												Transacao transacao = new Transacao(BigDecimal.valueOf(quantia), TipoTransacao.TRANSACAO_CREDITO, LocalDateTime.now());
												transacao.setConta(contaOrigem);
												TransacaoDAO.save(transacao);
												Transacao transacao2 = new Transacao(BigDecimal.valueOf(quantia), TipoTransacao.TRANSACAO_DEBITO, LocalDateTime.now());
									        	transacao2.setConta(contaDestino);
									        	TransacaoDAO.save(transacao2);
												
									        	Condao.update(contaOrigem);
									        	Condao.update(contaDestino);
									        	
									        	System.out.println("Transferencia realizada ");
									        	
									        } else {
									        	System.out.println("Conta origem ou destino não encontrada.");
									        }
										}	
										
								} catch (Exception e) {
									System.out.println("erro ao realizar a transferencia: " + e.getMessage());
									e.printStackTrace();
								}
								break;
							case 6:
								
								try {
									
										List<Conta> contas = cliente.getContas();
										
										if (contas.isEmpty()) {
											System.out.println("O cliente não possui contas");
										} else {
											System.out.println("lista de contas do cliente: ");
											for (Conta conta : contas) {
												System.out.println("Numero da Conta: " + conta.getNumeroconta());
												System.out.println("Saldo: " + conta.getSaldo());
												System.out.println("Tipo da Conta: " + conta.getTipoConta());
												System.out.println("Status: " + conta.isStatus());
												System.out.println("Data de Abertura: " + conta.getDataAbertura());
											
											}
										}
										System.out.println("Digite o numero da conta:");
										int numeroConta = scanner.nextInt();
										scanner.nextLine();
										
										IEntityDAO<Conta> Condao = new ContaDAO(new ConexaoBancoHibernate());
										Conta conta = Condao.findById(numeroConta);
										
										if (conta != null) {
											
											List<Transacao> transacoes = conta.getTransacoes();
											if (transacoes.isEmpty()) {
												System.out.println("Não há transações registradas");
											} else {
												System.out.println("Extrato da conta:");
												
												for (Transacao transacao : transacoes) {
													System.out.println("ID: " + transacao.getId());
													System.out.println("Data: " + transacao.getData());
													System.out.println("Valor: " + transacao.getValor());
													System.out.println("Tipo :" + transacao.getTipo());
												}
											}
										} else {
											System.out.println("Conta não encontrada");
										}							
								} catch (Exception e ) {
									System.out.println(" erro ao imprimir o extrato: " + e.getMessage());
									e.printStackTrace();
								}
								
							     break;
							case 7:
								
								try {
									
									
										List<Conta> contas = cliente.getContas();
										
										if (contas.isEmpty()) {
											System.out.println("O cliente não possui contas");
										} else {
											System.out.println("lista de contas:");
											for (Conta conta : contas) {
												System.out.println("numero da Conta: " + conta.getNumeroconta());
												System.out.println("Tipo da Conta: " + conta.getTipoConta());
												System.out.println("saldo: " + conta.getSaldo());
												System.out.println("Status: " + conta.isStatus());
												System.out.println("Data de Abertura: " + conta.getDataAbertura());
											}
										}
										System.out.println("Digite o numero da conta:");
										int numeroConta = scanner.nextInt();
										scanner.nextLine();
										
										IEntityDAO<Conta> Condao = new ContaDAO(new ConexaoBancoHibernate());
										Conta conta = Condao.findById(numeroConta);
										
										if (conta != null) {
											
											Condao.delete(conta);

											ClienteDao.update(cliente);
											System.out.println("Conta removida com sucesso.");
										} else {
											System.out.println("Conta não encontrada");
										}
			
								} catch (Exception e) {
									System.out.println("erro ao remover a conta: " + e.getMessage());
									e.printStackTrace();
								}
								
								break;
							default:
								System.out.println("Opção inválida");
								break;
							case 8:
								
			                    try {
			                    	
			                    		
			                    		List<Conta> contas = cliente.getContas();
			                    		BigDecimal saldoTotal = BigDecimal.ZERO;
			                    		
			                    		for (Conta conta : contas) {
			                    			saldoTotal = saldoTotal.add(conta.getSaldo());
			                    		}
			                    		System.out.println("Balanço total entre as contas: " + saldoTotal);     	
			                    } catch (Exception e) {
			                    	System.out.println("erro ao calcular o balanço: " + e.getMessage());
			                    	e.printStackTrace();
			                    }
								
								break;
							}
							
						} else {
							System.out.println("Cliente não encontrado");
						}
						
						} catch (Exception e) {
							System.out.println("erro ao buscar o cliente:" + e.getMessage());
							e.printStackTrace();
						}
						

						
						break;
					case 3:
						
						IEntityDAO<Cliente> Clidao = new ClienteDAO(new ConexaoBancoHibernate());
						
						List<Cliente> clientes = Clidao.findAll();
						
						for (Cliente cliente : clientes) {
							System.out.println("Nome:" + cliente.getNome());
							System.out.println("CPF:" + cliente.getCpf());
							
						}
						
						break;
					case 4:
						
						try {
							IEntityDAO<Cliente> ClienteDao2 = new ClienteDAO(new ConexaoBancoHibernate());
							
							List<Cliente> clientes1 = ClienteDao2.findAll();
							
							System.out.println("Lista de Clientes:");
							for (Cliente cliente : clientes1) {
					
								System.out.println("Nome: " + cliente.getNome());
								System.out.println("CPF: " + cliente.getCpf());
								System.out.println("==========");
							}
							
						} catch (Exception e) {
							System.out.println("erro ao listar clientes: " + e.getMessage());
							e.printStackTrace();
						}
						
						break;
					case 5:
						try {
						System.out.println("Digite o cpf do cliente que deseja excluir:");
						 cpf = scanner.next();
						scanner.nextLine();
						IEntityDAO<Cliente> ClienteDao2 = new ClienteDAO(new ConexaoBancoHibernate());
						
				
						Cliente cliente = ClienteDao2.findByCpf(cpf);
						
						if (cliente != null) {
							ClienteDao2.delete(cliente);
							System.out.println("Cliente excluído com sucesso");
						} else {
							System.out.println("Cliente não encontrado.");
						}
						
					} catch (Exception e) {
						System.out.println("Ocorreu um erro ao excluir o cliente: " + e.getMessage());
						e.printStackTrace();
					}
					
					break;
	                        
	                
					case 6:
						System.out.println("Até logo!");
						System.exit(0);
					default:
						System.out.println("Opção inválida");
						break;
					}
				}
		}
			
		}
					
					
}
