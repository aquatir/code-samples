defmodule EnchanterShop do

  def test_data do
  [
    %{title: "Longsword", price: 50, magic: false},
    %{title: "Healing Potion", price: 60, magic: true},
    %{title: "Rope", price: 10, magic: false},
    %{title: "Dragon's Spear", price: 100, magic: true},
  ]
  end

  @enchanter_name "Edwin"

  def enchant_for_sale_all([]), do: []
  def enchant_for_sale_all([item | incoming_items]) do
    new_item = %{
      title: "#{@enchanter_name}'s #{item.title}",
      price: item.price * 3,
      magic: true
    }
    [new_item | enchant_for_sale_all(incoming_items)]
  end

  # first match empty list to return nother
  # than on magic items => skip it by adding to the list and than enchanting all the rest
  # than enchant all that is left == non-magic items
  def enchant_for_sale_non_magic([]), do: []
  def enchant_for_sale_non_magic([item = %{magic: true} | incoming_items]) do
    [item | enchant_for_sale_non_magic(incoming_items)]
  end
  def enchant_for_sale_non_magic([item | incoming_items]) do
    new_item = %{
      title: "#{@enchanter_name}'s #{item.title}",
      price: item.price * 3,
      magic: true
    }
    [new_item | enchant_for_sale_non_magic(incoming_items)]
  end
end
