package com.brianchiu.jwtdemo.security;

import com.brianchiu.jwtdemo.dto.CustomerLoginRequest;
import com.brianchiu.jwtdemo.entity.Customer;
import com.brianchiu.jwtdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {

    @Autowired
    private CustomerService service;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtResponse createJwtToken(CustomerLoginRequest customerLoginRequest) throws Exception{

//        authenticate(email, password);

        final UserDetails userDetails = loadUserByUsername(customerLoginRequest.getEmail());

        String newGeneratedToken = jwtUtil.generateToken(userDetails);
        Customer customer = service.login(customerLoginRequest);

        return new JwtResponse(customer, newGeneratedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Customer customer = service.getCustomerByEmail(email);
        if(customer != null){
            return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(), getAuthorities(customer));
        } else {
            throw new UsernameNotFoundException("email is not valid");
        }
    }

    private Set<SimpleGrantedAuthority> getAuthorities(Customer customer){
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + customer.getRoleId()));
        return authorities;
    }

    private void authenticate(String email, String password) throws Exception{

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e){
            throw new Exception("customer is disable");
        } catch (BadCredentialsException e){
            throw new Exception("Bad credentials from customer");
        }
    }

}
