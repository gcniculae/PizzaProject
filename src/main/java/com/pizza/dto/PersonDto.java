package com.pizza.dto;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class PersonDto extends BaseEntityDto {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String address;

    public <T extends PersonDtoBuilder<T>> PersonDto(PersonDtoBuilder personDtoBuilder) {
        this.firstName = personDtoBuilder.firstName;
        this.lastName = personDtoBuilder.lastName;
        this.phoneNumber = personDtoBuilder.phoneNumber;
        this.dateOfBirth = personDtoBuilder.dateOfBirth;
        this.address = personDtoBuilder.address;
    }

    public static abstract class PersonDtoBuilder<T extends PersonDtoBuilder<T>> {

        private String firstName;
        private String lastName;
        private String phoneNumber;
        private LocalDate dateOfBirth;
        private String address;

        public abstract T getThisDto();

        public T setFirstNameForDto(String firstName) {
            this.firstName = firstName;
            return this.getThisDto();
        }

        public T setLastNameForDto(String lastName) {
            this.lastName = lastName;
            return this.getThisDto();
        }

        public T setPhoneNumberForDto(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this.getThisDto();
        }

        public T setDateOfBirthForDto(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this.getThisDto();
        }

        public T setAddressForDto(String address) {
            this.address = address;
            return this.getThisDto();
        }

        public PersonDto buildDto() {
            return new PersonDto(this);
        }
    }
}
