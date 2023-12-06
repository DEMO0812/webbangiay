package com.poly.service;


//@Service
//public class UserService implements UserDetailsService{
//	@Autowired
//	AccountDAO aDao;
//	
//	@Autowired
//	BCryptPasswordEncoder pe;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		try {
//			Account account = aDao.findById(username).get();
//			//tạo userDetails từ account
//			String password = account.getPassword();
//			String[] roles = account.getAuthorities().stream()
//					.map(au -> au.getRole().getId())
//					.collect(Collectors.toList()).toArray(new String[0]);
//			return User.withUsername(username).password(pe.encode(password)).roles(roles).build();
//		} catch (Exception e) {
//			throw new UsernameNotFoundException(username+ "not found! / securityConfig");
//		}
//	}
//
//}
